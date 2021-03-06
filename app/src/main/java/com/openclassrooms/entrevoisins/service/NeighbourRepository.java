package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client (modified because it is not a real api service)
 */
public interface NeighbourRepository {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);


    Neighbour getNeighboursById(long userId);

    /**
     *
     * create a favorite
     * @param neighbour
     */

    void removeFavorite(long neighbour);

    void addFavorite(long neighbour);

    List<Neighbour> getFavoriteNeighbours();
}
