package com.zjava.service;

import com.zjava.model.Passenger;
import com.zjava.repository.PassengerRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rafal Lebioda on 20.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@ActiveProfiles({"test"})
@Log4j2
public class PassengerServiceTest {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerRepository passengerRepository;

    private Passenger passenger1;
    private Passenger passenger2;
    private Passenger passenger3;

    @Before
    public void setUp(){

        passenger1=new Passenger();
        passenger2=new Passenger();
        passenger3=new Passenger();
        passenger3.setFirstName("user");
        passenger3.setPesel("123456");

        passengerRepository.save(passenger1);
        passengerRepository.save(passenger2);
    }

    @Test
    public void findAllPassengersTest() {
        // preparation
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger());
        passengers.add(new Passenger());
        List<Passenger> passengersFromRepository;

        // act
        passengersFromRepository = passengerService.findAllPassenger();

        // assert
        assertEquals("Size of returned list will be the same as the size of prepared list of passengers", passengers.size(), passengersFromRepository.size());

    }

    @After
    public void cleanUp() {
        passengerRepository.deleteAll();
    }


}
