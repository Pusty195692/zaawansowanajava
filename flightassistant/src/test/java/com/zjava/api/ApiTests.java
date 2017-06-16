package com.zjava.api;

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
        GetData getData = new GetData();
        List<AircraftType> aircraftTypes = getData.getObjectsList(new AircraftType(), AircraftType.class,  "v1", "aircraftTypes");
        assertEquals(aircraftTypes.get(0).getClass(), AircraftType.class);
    }

    @Test
    public void getAirlines() throws Exception {
        GetData getData = new GetData();
        List<Airline> airlines = getData.getObjectsList(new Airline(), Airline.class,  "v1", "airlines");
        assertEquals(airlines.get(0).getClass(), Airline.class);
    }

    @Test
    public void getDestinations() throws Exception {
        GetData getData = new GetData();
        List<Destination> destinations = getData.getObjectsList(new Destination(), Destination.class,  "v1", "destinations");
        assertEquals(destinations.get(0).getClass(), Destination.class);
    }

    @Test
    public void getFlights() throws Exception {
        GetData getData = new GetData();
        List<Flight> flights = getData.getObjectsList(new Flight(), Flight.class,  "v3", "flights");
        assertEquals(flights.get(0).getClass(), Flight.class);
    }


}
