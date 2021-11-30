package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourRepository implements NeighbourRepository {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
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
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * boucle pour parcourir la liste des neighbours et retourner le voisin qui correspond à la position (grâce au parametre)
     *
     * @param userId
     */
    @Override
    public Neighbour getNeighboursById(long userId) {

        for (Neighbour neighbour : neighbours) {
            if (neighbour.getId() == userId)
                return neighbour;

        }
        return null;
    }

    @Override
    public void removeFavorite(long userId) {
        Neighbour neighbour = getNeighboursById(userId);
        neighbour.setFavorite(false);

    }

    @Override
    public void addFavorite(long userId) {
        Neighbour neighbour = getNeighboursById(userId);
        neighbour.setFavorite(true);

    }

    /**
     * boucle à partir de la liste des favoris pour remonter ceux qui ont la condition if  = favorite
     */

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteNeighbourList = new ArrayList<>();

        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favoriteNeighbourList.add(neighbour);
            }
        }
        return favoriteNeighbourList;


    }
}
