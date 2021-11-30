package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourRepository service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbour() {
        Neighbour neighbourToCreate = new Neighbour(013, "Toto", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "6, chemin de la Tuilerie", "911", "pas de marche Nordique");
        service.createNeighbour(neighbourToCreate);
        List<Neighbour> neighbours = service.getNeighbours();
        assertTrue(neighbours.contains(neighbourToCreate));
    }

    @Test
    public void getFavoriteNeighbours() {
        Neighbour neighbourToBeFavorite = service.getNeighbours().get(0);
        neighbourToBeFavorite.setFavorite(true);
        assertTrue(service.getFavoriteNeighbours().contains(neighbourToBeFavorite));
    }

    @Test
    public void removeFavorite() {
        Neighbour removedFavoriteNeighbour= service.getNeighboursById(001);
        service.removeFavorite(removedFavoriteNeighbour.getId());
        assertFalse(service.getFavoriteNeighbours().contains(removedFavoriteNeighbour));
    }

    @Test
    public void addFavorite() {
        Neighbour addedFavoriteNeighbour = service.getNeighboursById(001);
        service.addFavorite(addedFavoriteNeighbour.getId());
        assertTrue(service.getFavoriteNeighbours().contains(addedFavoriteNeighbour));

    }

    @Test
    public void getNeighboursById() {
        Neighbour neighbourWithId= new  Neighbour(013, "Toto", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "6, chemin de la Tuilerie", "911", "pas de marche Nordique");
        service.getNeighboursById(013);
        List<Neighbour> neighbours = service.getNeighbours();
        assertTrue(neighbours.contains(neighbourWithId));
    }

}
