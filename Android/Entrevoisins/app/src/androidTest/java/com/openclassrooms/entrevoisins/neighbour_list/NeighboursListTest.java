
package com.openclassrooms.entrevoisins.neighbour_list;

import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.ListNeighbourHelper;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private ListNeighbourActivity mActivity;
    private NeighbourApiService service;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        service = DI.getNewInstanceApiService();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // all neighbour list
        ListNeighbourHelper.getAllNeighbour()
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myFavoriteNeighboursList_shouldBeEmpty() {
        // favorite list is empty
        ListNeighbourHelper.getFavoriteNeighbour()
                .check(withItemCount(0));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        ListNeighbourHelper.getAllNeighbour().check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        ListNeighbourHelper.getAllNeighbour()
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        ListNeighbourHelper.getAllNeighbour().check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighboursList_clickOnItem_shouldOpenDetail() {
        // When perform a click on a item
        ListNeighbourHelper.getAllNeighbour()
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.detail_name)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void myNeighboursList_clickOnFirstItem_shouldShowFirstName() {
        // When perform a click on a item
        ListNeighbourHelper.getAllNeighbour()
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        String neighbourName = service.getNeighbours(false).get(0).getName();
        onView(ViewMatchers.withId(R.id.detail_name)).check(matches(withText(neighbourName)));
    }

    @Test
    public void myNeighboursList_CountFavorites() {
        ListNeighbourHelper.getAllNeighbour()
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.floatingActionButtonFavorite)).perform(scrollTo(), click());
        Espresso.pressBack();
        ListNeighbourHelper.getAllNeighbour().perform(swipeLeft());
        ListNeighbourHelper.getFavoriteNeighbour()
                .check(withItemCount(1));
    }

}