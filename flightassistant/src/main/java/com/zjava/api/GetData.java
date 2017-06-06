package com.zjava.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjava.model.AircraftType;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by Adrian on 2017-06-06.
 */
public class GetData {
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

