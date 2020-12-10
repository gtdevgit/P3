package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Log.d("mydebug", "ListNeighbourPagerAdapter.getItem(position = " + position + ")");
        // position = 0 => all the neighbours
        // position = 1 => only the favorites neighbours
        return NeighbourFragment.newInstance(position == 1);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() { return 2;
    }
}