package com.zjava.model.elements;

import com.zjava.controller.DataController;
import com.zjava.repository.elements.DestinationRepository;
import com.zjava.service.elements.DestinationService;
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
public class DestinationServiceTest {
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private DestinationRepository destinationRepository;

    List<Destination> fromApi;
    List<Destination> fromRepoBefore;

    @Before
    public void beforeTests() throws Exception {
        DataController dataController = new DataController();
        fromApi = dataController.getObjectsList(new Destination(), Destination.class,  "v1", "destinations");
        fromRepoBefore = destinationService.findAll();
        for(Destination destination : fromApi) {
            destinationService.save(destination);
        }
    }

    @After
    public void afterTests() {
        destinationService.deleteAll();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Destination> fromCRUD = destinationService.findAll();
        long count = destinationService.count();
        assertEquals(fromApi.size() + fromRepoBefore.size(), fromCRUD.size());
    }
}
