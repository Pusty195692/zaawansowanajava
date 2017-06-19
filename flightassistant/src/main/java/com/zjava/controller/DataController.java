package com.zjava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zjava.model.elements.AircraftType;
import com.zjava.model.elements.Airline;
import com.zjava.model.elements.Destination;
import com.zjava.model.elements.Flight;
import com.zjava.repository.elements.FlightRepository;
import com.zjava.service.elements.AircraftService;
import com.zjava.service.elements.AirlineService;
import com.zjava.service.elements.DestinationService;
import com.zjava.service.elements.FlightService;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 2017-06-06.
 */
@Controller("/admin/home/updateDatabase")
@Log4j2
public class DataController {

    @Autowired
    AircraftService aircraftService;
    @Autowired
    AirlineService airlineService;
    @Autowired
    DestinationService destinationService;
    @Autowired
    FlightService flightService;


    /**
     * Metoda zwracajaca liste obiektow rozpoznawalnych dla aplikacji. Typ obiektow okreslony jest parametrami funkcji
     *
     * @param typeDef    Typ dla ktorego zwracana bedzie lista, uzycie: new NazwaKlasy()
     * @param clazz      uzycie: NazwaKlasy.class
     * @param apiVersion wersja API (Dla Flights - "v3" dla pozostalych - "v1")
     * @param objectType nazwa typu w liczbie mnogiej ("flights", "destinations", "aircraftTypes" lub "airlines")
     * @param <T>        Typ danych
     * @return Zwraca liste obiektow na ktorych operowac moze aplikacja
     * @throws Exception
     */
    public <T> List<T> getObjectsList(T typeDef, Class clazz, String apiVersion, String objectType) throws Exception {
        String whichContent = "public-flights/" + objectType.toLowerCase();
        HttpResponse httpResponse = getResponse(whichContent, apiVersion);
        JSONArray elements = getJSONArrayFromResponse(httpResponse, objectType);

        String json = elements.toString();

        List<T> list;
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(json);
        TypeFactory t = TypeFactory.defaultInstance();
        list = mapper.readValue(json, t.constructCollectionType(ArrayList.class, clazz));

        System.out.println(list);
        System.out.println(list.get(0).getClass());
        return list;
    }

    /**
     * Na podstawie odpowiedzi HTTP zwraca obiekt typu JSONArray zawierajacy dane
     *
     * @param response   odpowiedz HTTP
     * @param objectType nazwa typu w liczbie mnogiej ("flights", "destinations", "aircraftTypes" lub "airlines")
     * @return tablica JSONArray zawierajaca loty, kierunki, samoloty badz linie lotnicze
     */
    private JSONArray getJSONArrayFromResponse(HttpResponse response, String objectType) {
        JSONArray elements = new JSONArray();
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
                elements = (JSONArray) jsonObject.get(objectType);
            } else {
                System.out.println(
                        "Oops something went wrong\nHttp response code: " + response.getStatusLine().getStatusCode() + "\nHttp response body: "
                                + EntityUtils.toString(response.getEntity()));
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return elements;
    }

    /**
     * Pobiera odpowiedz z API
     *
     * @param whichContent fragment URL charakterystyczny dla danego typu pobieranych danych
     * @param apiVersion   wersja API (Dla Flights - "v3" dla pozostalych - "v1")
     * @return zwraca odpowiedz HTTP na podstawie ktorej dzieki metodzie getJSONArrayFromResponse mozemy uzyskac dane w postaci obiektu typu JSONArray
     * @throws IOException
     */
    private HttpResponse getResponse(String whichContent, String apiVersion) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.schiphol.nl/" + whichContent +
                "?app_id=4c071d3a" +
                "&app_key=5dbc886c565058c07bef8553177d464b");
        request.addHeader("ResourceVersion", apiVersion);
        return httpClient.execute(request);
    }

    @RequestMapping(value = "admin/updateDatabase/updateAircrafts", method = RequestMethod.GET)
    public String getAircraftTypes(Model model) throws Exception {
        List<AircraftType> fromRepo = aircraftService.findAll();
        List<AircraftType> fromApi = getObjectsList(new AircraftType(), AircraftType.class, "v1", "aircraftTypes");
        for (AircraftType a : fromApi) {
            if (!fromRepo.contains(a)) {
                aircraftService.save(a);
            }
        }
        model.addAttribute("aircrafts", aircraftService.findAll());
        return "elements/aircraftsList";
    }

    @RequestMapping(value = "admin/updateDatabase/updateAirlines", method = RequestMethod.GET)
    public String getAirlines(Model model) throws Exception {
        List<Airline> fromRepo = airlineService.findAll();
        List<Airline> fromApi = getObjectsList(new Airline(), Airline.class, "v1", "airlines");
        for (Airline a : fromApi) {
            if (!fromRepo.contains(a)) {
                airlineService.save(a);
            }
        }
        model.addAttribute("airlines", airlineService.findAll());
        return "elements/airlinesList";
    }

    @RequestMapping(value = "admin/updateDatabase/updateDestinations", method = RequestMethod.GET)
    public String getDestinations(Model model) throws Exception {
        List<Destination> fromRepo = destinationService.findAll();
        List<Destination> fromApi = getObjectsList(new Destination(), Destination.class, "v1", "destinations");
        for (Destination d : fromApi) {
            if (!fromRepo.contains(d)) {
                destinationService.save(d);
            }
        }
        model.addAttribute("destinations", destinationService.findAll());
        return "elements/destinationsList";
    }

    @RequestMapping(value = "admin/updateDatabase/updateFlights", method = RequestMethod.GET)
    public String getFlights(Model model) throws Exception {
        List<Flight> fromRepo = flightService.findAll();
        List<Flight> flights = getObjectsList(new Flight(), Flight.class, "v3", "flights");
        for (Flight f : flights) {
            if (!fromRepo.contains(f)) {
                flightService.save(f);
            }
        }
        model.addAttribute("flights", flightService.findAll());
        return "elements/flightsList";
    }

//    @RequestMapping(value = "aircraftTypes", method = RequestMethod.GET)
//    public String messages(Model model) {
//        model.addAttribute("aircraftTypes", aircraftService.findAll());
//        return "aircraftTypes/list";
//    }

    /**********************************************************STARE METODY*******************************************************************/


    private JSONArray getDataJSONArray(String whichContent, String apiVersion, String objectType) throws org.json.simple.parser.ParseException {
        JSONArray elements = new JSONArray();
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("https://api.schiphol.nl/" + whichContent +
                    "?app_id=4c071d3a" +
                    "&app_key=5dbc886c565058c07bef8553177d464b");
            request.addHeader("ResourceVersion", apiVersion);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
                elements = (JSONArray) jsonObject.get(objectType);
                System.out.println("found " + elements.size() + " " + objectType);
                elements.forEach(System.out::println);
            } else {
                System.out.println(
                        "Oops something went wrong\nHttp response code: " + response.getStatusLine().getStatusCode() + "\nHttp response body: "
                                + EntityUtils.toString(response.getEntity()));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops something went wrong\nPlease insert your APP_ID and APP_KEY as arguments");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return elements;
    }

    public JSONArray getFlightsJSONArray() throws org.json.simple.parser.ParseException {
        JSONArray jsonArray = getDataJSONArray("public-flights/flights", "v3", "flights");
        return jsonArray;
    }

    public JSONArray getDestinationsJSONArray() throws org.json.simple.parser.ParseException {
        JSONArray jsonArray = getDataJSONArray("public-flights/destinations", "v1", "destinations");
        return jsonArray;
    }

    public JSONArray getAircraftTypesJSONArray() throws org.json.simple.parser.ParseException {
        JSONArray jsonArray = getDataJSONArray("public-flights/aircrafttypes", "v1", "aircraftTypes");
        return jsonArray;
    }

    public JSONArray getAirlinesJSONArray() throws org.json.simple.parser.ParseException {
        JSONArray jsonArray = getDataJSONArray("public-flights/airlines", "v1", "airlines");
        return jsonArray;
    }
}

