package com.zjava.model.elements;

import com.zjava.controller.DataController;
import com.zjava.repository.elements.AircraftTypeRepository;
import com.zjava.service.elements.AircraftService;
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
public class AircraftTypeServiceTest {
    @Autowired
    private AircraftService aircraftService;

    List<AircraftType> fromApi;
    List<AircraftType> fromRepoBefore;

    @Before
    public void beforeTests() throws Exception {
        DataController dataController = new DataController();
        fromApi = dataController.getObjectsList(new AircraftType(), AircraftType.class,  "v1", "aircraftTypes");
        fromRepoBefore = aircraftService.findAll();
        for(AircraftType aircraftType : fromApi) {
            aircraftService.save(aircraftType);
        }
    }

    @After
    public void afterTests() {
        aircraftService.deleteAll();
    }

    @Test
    public void testFindAll() throws Exception {
        List<AircraftType> fromCRUD = aircraftService.findAll();
        assertEquals(fromApi.size() + fromRepoBefore.size(), fromCRUD.size());
    }

    @Test
    public void testCount() throws Exception {
        List<AircraftType> fromCRUD = aircraftService.findAll();
        assertEquals(fromApi.size() + fromRepoBefore.size(), aircraftService.count());
    }

    @Test
    public void deleteAll() throws Exception {
        aircraftService.deleteAll();
        assertEquals(0, aircraftService.count());
    }
}
