package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getAllNeighbours() {
        List<Neighbour> neighbours = service.getNeighbours(false);
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getFavoritesNeighbours() {
        List<Neighbour> neighbours = service.getNeighbours(true);
        int Count = neighbours.size();
        assertEquals(Count, 0);
    }

    @Test
    public void deleteNeighbour() {
        Neighbour neighbourToDelete = service.getNeighbours(false).get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours(false).contains(neighbourToDelete));
    }

    @Test
    public void createOneNeighbour() {
        Neighbour n = new Neighbour(99, "name", "url", "adress", "phoneNumber", "aboutMe", false);
        assertNotNull(n);
    }

    @Test
    public void addOneNeighbour() {
        List<Neighbour> neighbours = service.getNeighbours(false);
        int firstCount = neighbours.size();
        Neighbour n = new Neighbour(99, "name", "url", "adress", "phoneNumber", "aboutMe", false);
        service.createNeighbour(n);
        int secondCount = neighbours.size();
        assertEquals(firstCount + 1, secondCount);
    }

    @Test
    public void addOneFavoriteNeighbour() {
        Neighbour n = new Neighbour(99, "name", "url", "adress", "phoneNumber", "aboutMe", true);
        service.createNeighbour(n);
        List<Neighbour> neighbours = service.getNeighbours(true);
        int Count = neighbours.size();
        assertEquals(Count, 1);
    }

    @Test
    public void setNeighbourAsFavorite() {
        service.getNeighbours(false).get(0).toggleFavoriteStatus();
        assertEquals(service.getNeighbours(false).get(0).isFavorite(), true);
    }

    @Test
    public void findNeighbourById()
    {
        // Prend le premier neighbour de la liste, puis le recherche à partir de son Id, on doit trouver le même objet.
        Neighbour firstNeighbour = service.getNeighbours(false).get(0);
        Neighbour neighbourFind = service.findNeighbourById(firstNeighbour.getId());
        assertEquals(firstNeighbour, neighbourFind);
    }

}
