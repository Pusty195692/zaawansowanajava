package com.zjava.model.elements;

import com.zjava.controller.DataController;
import com.zjava.repository.elements.AirlineRepository;
import com.zjava.service.elements.AirlineService;
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
public class AirlineServiceTest {
    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirlineRepository airlineRepository;

    List<Airline> fromApi;

    @Before
    public void beforeTests() throws Exception {
        DataController dataController = new DataController();
        fromApi = dataController.getObjectsList(new Airline(), Airline.class,  "v1", "airlines");
        for(Airline airline : fromApi) {
            airlineService.save(airline);
        }
    }

    @After
    public void afterTests() {
        airlineService.deleteAll();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Airline> fromCRUD = airlineService.findAll();
        long count = airlineService.count();
        assertEquals(fromApi.size(), fromCRUD.size());
    }
}
