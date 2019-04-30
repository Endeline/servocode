package com.kubiczak.servocode.service;

import com.kubiczak.servocode.model.PlaceEntity;
import com.kubiczak.servocode.repository.PlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Test class for integration service with repository.
 * @author Henryk Kubiczak.
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaceServiceRepositoryTest {
    @Mock
    private PlaceRepository repository;

    @InjectMocks
    private PlaceServiceImpl service;

    /**
     * Test for check response entity when get empty collection.
     */
    @Test
    public void shouldGetEmptyCollectionWhenRepositoryFindAllThenReturnEmptyList() {
        given(repository.findAll())
                .willReturn(Collections.emptyList());

        Collection<PlaceEntity> entities = service.getAll();

        assertEquals(0, entities.size());
    }

    /**
     * Test for check response entity when get all entities.
     */
    @Test
    public void shouldGetCollectionPlaceWhenRepositoryFindAllThenReturnCollectionOfPlace() {
        PlaceEntity entity = new PlaceEntity(0, "a", 1.0, 2.0);
        PlaceEntity entity1 = new PlaceEntity(1, "b", 3.0, 4.0);

        given(repository.findAll())
                .willReturn(Arrays.asList(entity, entity1));

        Collection<PlaceEntity> entities = service.getAll();
        Object[] response = entities.toArray();

        assertEquals(2, entities.size());
        assertEquals(entity.getId(), ((PlaceEntity)response[0]).getId());
        assertEquals(entity.getName(), ((PlaceEntity)response[0]).getName());
        assertEquals(entity.getLatitude(), ((PlaceEntity)response[0]).getLatitude(), 0);
        assertEquals(entity.getLongitude(), ((PlaceEntity)response[0]).getLongitude(), 0);
        assertEquals(entity1.getId(), ((PlaceEntity)response[1]).getId());
        assertEquals(entity1.getName(), ((PlaceEntity)response[1]).getName());
        assertEquals(entity1.getLatitude(), ((PlaceEntity)response[1]).getLatitude(), 0);
        assertEquals(entity1.getLongitude(), ((PlaceEntity)response[1]).getLongitude(), 0);
    }

    /**
     * Test for check response entity when user save new place.
     */
    @Test
    public void shouldGetSavedEntityWhenRepositorySaveThenReturnSavedEntity() {
        PlaceEntity entity = new PlaceEntity(0, "a", 1.0, 2.0);

        given(repository.save(entity))
                .willReturn(entity);

        PlaceEntity response = service.save(entity);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
        assertEquals(entity.getLatitude(), response.getLatitude(), 0);
        assertEquals(entity.getLongitude(), response.getLongitude(), 0);
    }

    /**
     * Test for check response entity when get closer point by place entity.
     */
    @Test
    public void shouldGetCloserPointByEntityWhenRepositoryFindAllThenReturnCloserPoint() {
        PlaceEntity entity = new PlaceEntity(0, "a", 1.0, 2.0);
        PlaceEntity entity1 = new PlaceEntity(1, "b", 100.0, 200.0);

        given(repository.findAll())
                .willReturn(Arrays.asList(entity, entity1));

        PlaceEntity response = service.getCloserPoint(entity);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
        assertEquals(entity.getLatitude(), response.getLatitude(), 0);
        assertEquals(entity.getLongitude(), response.getLongitude(), 0);
    }

    /**
     * Test for check response entity when get closer point by latitude and longitude.
     */
    @Test
    public void shouldGetCloserPointByLatLngWhenRepositoryFindAllThenReturnCloserPoint() {
        PlaceEntity entity = new PlaceEntity(0, "a", 1.0, 2.0);
        PlaceEntity entity1 = new PlaceEntity(1, "b", 100.0, 200.0);

        given(repository.findAll())
                .willReturn(Arrays.asList(entity, entity1));

        PlaceEntity response = service.getCloserPoint(1.0, 1.1);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
        assertEquals(entity.getLatitude(), response.getLatitude(), 0);
        assertEquals(entity.getLongitude(), response.getLongitude(), 0);
    }
}
