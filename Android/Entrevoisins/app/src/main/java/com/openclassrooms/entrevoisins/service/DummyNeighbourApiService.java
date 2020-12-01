package com.openclassrooms.entrevoisins.service;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */

    @Override
    public List<Neighbour> getNeighbours(Boolean OnlyFavorite) {
        if (!OnlyFavorite) {
            return neighbours;
        } else {
            List<Neighbour> favorite_neighbours = new ArrayList<Neighbour>();
            for (Neighbour neighbour : neighbours ) {
                if (neighbour.isFavorite()) {
                    favorite_neighbours.add(neighbour);
                }
            }
            return favorite_neighbours;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour findNeighbourById(long id) {
        for (Neighbour neighbour : neighbours ) {
            if (neighbour.getId() == id) {
                return neighbour;
            }
        }
        return null;
    }
}
