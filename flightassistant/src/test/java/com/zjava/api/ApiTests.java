package com.zjava.api;

import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * Created by Adrian on 2017-06-06.
 */
public class ApiTests {

    @Test
    public void testRetrievingFlights() throws ParseException {
        GetData getData = new GetData();
        getData.getFlightsJSONArray();
    }

    @Test
    public void testRetrievingAircraftTypes() throws ParseException {
        GetData getData = new GetData();
        getData.getAircraftTypesJSONArray();
    }

    @Test
    public void testRetrievingDestinations() throws ParseException {
        GetData getData = new GetData();
        getData.getDestinationsJSONArray();
    }

    @Test
    public void testRetrievingsAirlines() throws ParseException {
        GetData getData = new GetData();
        getData.getAirlinesJSONArray();
    }
}
