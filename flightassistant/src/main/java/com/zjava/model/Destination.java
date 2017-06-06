package com.zjava.model;

import java.util.HashMap;

/**
 * Created by Adrian on 2017-06-06.
 */
public class Destination {
    String country;
    int schemaVersion;
    String iata;
    String city;
    HashMap<String, String> publicName;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public HashMap<String, String> getPublicName() {
        return publicName;
    }

    public void setPublicName(HashMap<String, String> publicName) {
        this.publicName = publicName;
    }
}
