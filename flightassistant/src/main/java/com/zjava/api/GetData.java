package com.zjava.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 2017-06-06.
 */
public class GetData {

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
        return getDataJSONArray("public-flights/flights", "v3", "flights");
    }

    public JSONArray getDestinationsJSONArray() throws org.json.simple.parser.ParseException {
        return getDataJSONArray("public-flights/destinations", "v1", "destinations");
    }

    public JSONArray getAircraftTypesJSONArray() throws org.json.simple.parser.ParseException {
        return getDataJSONArray("public-flights/aircrafttypes", "v1", "aircraftTypes");
    }

    public JSONArray getAirlinesJSONArray() throws org.json.simple.parser.ParseException {
        return getDataJSONArray("public-flights/airlines", "v1", "airlines");
    }
}

