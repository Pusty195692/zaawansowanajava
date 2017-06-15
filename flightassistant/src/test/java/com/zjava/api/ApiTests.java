package com.zjava.api;

import com.zjava.model.AircraftType;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
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
        List<AircraftType> aircraftTypes = getData.getObjectsList(new AircraftType(), AircraftType.class,  "v1", "aircraftTypes");
        assertEquals(aircraftTypes.get(0).getClass(), AircraftType.class);
    }

    @Test
    public void getDestinations() throws Exception {
        GetData getData = new GetData();
        List<AircraftType> aircraftTypes = getData.getObjectsList(new AircraftType(), AircraftType.class,  "v1", "aircraftTypes");
        assertEquals(aircraftTypes.get(0).getClass(), AircraftType.class);
    }

    @Test
    public void getFlights() throws Exception {
        GetData getData = new GetData();
        List<AircraftType> aircraftTypes = getData.getObjectsList(new AircraftType(), AircraftType.class,  "v1", "aircraftTypes");
        assertEquals(aircraftTypes.get(0).getClass(), AircraftType.class);
    }


}
