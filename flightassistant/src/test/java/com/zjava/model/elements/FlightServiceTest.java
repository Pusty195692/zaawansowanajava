package com.zjava.model.elements;

import com.zjava.controller.DataController;
import com.zjava.repository.elements.FlightRepository;
import com.zjava.service.elements.FlightService;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adrian on 2017-06-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@PropertySource("classpath:application-test.properties ")
@Log4j2
public class FlightServiceTest {
    @Autowired
    private FlightService flightService;

    List<Flight> fromApi;
    List<Flight> fromRepoBefore;
    @Before
    public void beforeTests() throws Exception {
        DataController dataController = new DataController();
        fromApi = dataController.getObjectsList(new Flight(), Flight.class,  "v3", "flights");
        fromRepoBefore = flightService.findAll();
        for(Flight flight : fromApi) {
            flightService.save(flight);
        }
    }

    @After
    public void afterTests() {
        flightService.deleteAll();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Flight> fromCRUD = flightService.findAll();
        assertEquals(fromApi.size() + fromRepoBefore.size(), fromCRUD.size());
    }

    @Test
    public void testCount() throws Exception {
        List<Flight> fromCRUD = flightService.findAll();
        assertEquals(fromApi.size() + fromRepoBefore.size(), flightService.count());
    }

    @Test
    public void deleteAll() throws Exception {
        flightService.deleteAll();
        assertEquals(0, flightService.count());
    }
}
