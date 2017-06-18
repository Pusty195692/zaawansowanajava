package com.zjava.api;

import com.zjava.controller.DataController;
import com.zjava.model.elements.AircraftType;
import com.zjava.model.elements.Airline;
import com.zjava.model.elements.Destination;
import com.zjava.model.elements.Flight;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adrian on 2017-06-06.
 */
public class ApiTests {

    @Test
    public void getAircraftTypes() throws Exception {
        DataController dataController = new DataController();
        List<AircraftType> aircraftTypes = dataController.getObjectsList(new AircraftType(), AircraftType.class,  "v1", "aircraftTypes");
        assertEquals(aircraftTypes.get(0).getClass(), AircraftType.class);
    }

    @Test
    public void getAirlines() throws Exception {
        DataController dataController = new DataController();
        List<Airline> airlines = dataController.getObjectsList(new Airline(), Airline.class,  "v1", "airlines");
        assertEquals(airlines.get(0).getClass(), Airline.class);
    }

    @Test
    public void getDestinations() throws Exception {
        DataController dataController = new DataController();
        List<Destination> destinations = dataController.getObjectsList(new Destination(), Destination.class,  "v1", "destinations");
        assertEquals(destinations.get(0).getClass(), Destination.class);
    }

    @Test
    public void getFlights() throws Exception {
        DataController dataController = new DataController();
        List<Flight> flights = dataController.getObjectsList(new Flight(), Flight.class,  "v3", "flights");
        assertEquals(flights.get(0).getClass(), Flight.class);
    }


}
