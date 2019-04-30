package com.kubiczak.servocode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubiczak.servocode.model.PlaceEntity;
import com.kubiczak.servocode.service.PlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

/**
 * Test class for integration service with controller and test rest method.
 * @author Henryk Kubiczak.
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PlaceController.class)
public class PlaceControllerServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlaceService service;

    private final String BASE_URL = "/api/place/";

    /**
     * Test for check working http request for get null collection of place entity.
     * @throws Exception mvc perform.
     */
    @Test
    public void givenEmptyCollectionWhenGetAllThenReturnResponseEntity() throws Exception {
        given(service.getAll())
                .willReturn(Collections.emptyList());

        mvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /**
     * Test for check working http request for get collection of place entity.
     * @throws Exception mvc perform.
     */
    @Test
    public void givenCollectionWhenGetAllWillReturnCollectionWithEntity() throws Exception {
        PlaceEntity entity = new PlaceEntity(0, "a", 1.0, 2.0);
        PlaceEntity entity1 = new PlaceEntity(1, "b", 3.0, 4.0);

        given(service.getAll())
                .willReturn(Arrays.asList(entity, entity1));

        mvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is((int)entity.getId())))
                .andExpect(jsonPath("$[0].name", is(entity.getName())))
                .andExpect(jsonPath("$[0].latitude", is(entity.getLatitude())))
                .andExpect(jsonPath("$[0].longitude", is(entity.getLongitude())))
                .andExpect(jsonPath("$[1].id", is((int)entity1.getId())))
                .andExpect(jsonPath("$[1].name", is(entity1.getName())))
                .andExpect(jsonPath("$[1].latitude", is(entity1.getLatitude())))
                .andExpect(jsonPath("$[1].longitude", is(entity1.getLongitude())));
    }

    /**
     * Test for check working http request for save current place entity.
     * @throws Exception mvc perform.
     */
    @Test
    public void givenSavedEntityWhenSaveThenReturnSavedEntity() throws Exception {
        PlaceEntity entity = new PlaceEntity(1, "a", 2.0, 3.0);

        given(service.save(any(PlaceEntity.class)))
                .willReturn(entity);

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)entity.getId())))
                .andExpect(jsonPath("$.name", is(entity.getName())))
                .andExpect(jsonPath("$.latitude", is(entity.getLatitude())))
                .andExpect(jsonPath("$.longitude", is(entity.getLongitude())));
    }

    /**
     * Test for check working http request for save collection of place entity.
     * @throws Exception mvc perform.
     */
    @Test
    public void givenCollectionEntityWhenSaveAllThenReturnSavedEntities() throws Exception {
        PlaceEntity entity = new PlaceEntity(1, "a", 2.0, 3.0);
        PlaceEntity entity1 = new PlaceEntity(4, "b", 5.0, 6.0);

        List<PlaceEntity> entities = Arrays.asList(entity, entity1);

        given(service.save(entities))
                .willReturn(entities);

        mvc.perform(post(BASE_URL + "places")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entities)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is((int)entity.getId())))
                .andExpect(jsonPath("$[0].name", is(entity.getName())))
                .andExpect(jsonPath("$[0].latitude", is(entity.getLatitude())))
                .andExpect(jsonPath("$[0].longitude", is(entity.getLongitude())))
                .andExpect(jsonPath("$[1].id", is((int)entity1.getId())))
                .andExpect(jsonPath("$[1].name", is(entity1.getName())))
                .andExpect(jsonPath("$[1].latitude", is(entity1.getLatitude())))
                .andExpect(jsonPath("$[1].longitude", is(entity1.getLongitude())));

    }

    /**
     * Test for check working http request for get closer point by place entity.
     * @throws Exception mvc perform.
     */
    @Test
    public void givenCloserPointWhenGetCloserPointByEntityReturnCloserEntity() throws Exception {
        PlaceEntity entity = new PlaceEntity(1, "a", 2.0, 3.0);

        given(service.getCloserPoint(any(PlaceEntity.class)))
                .willReturn(entity);

        mvc.perform(post(BASE_URL + "find")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entity)))
                .andExpect(jsonPath("$.id", is((int)entity.getId())))
                .andExpect(jsonPath("$.name", is(entity.getName())))
                .andExpect(jsonPath("$.latitude", is(entity.getLatitude())))
                .andExpect(jsonPath("$.longitude", is(entity.getLongitude())));
    }

    /**
     * Test for check working http request for get closer point by latitude and longitude.
     * @throws Exception mvc perform.
     */
    @Test
    public void givenCloserPointWhenGetCloserPointByLatLngReturnCloserPoint() throws Exception {
        PlaceEntity entity = new PlaceEntity(1, "a", 2.0, 3.0);

        given(service.getCloserPoint(any(Double.class), any(Double.class)))
                .willReturn(entity);

        mvc.perform(get(BASE_URL + "/find")
                .param("latitude", "1.0")
                .param("longitude", "2.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)entity.getId())))
                .andExpect(jsonPath("$.name", is(entity.getName())))
                .andExpect(jsonPath("$.latitude", is(entity.getLatitude())))
                .andExpect(jsonPath("$.longitude", is(entity.getLongitude())));
    }
}
