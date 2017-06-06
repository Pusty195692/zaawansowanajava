package com.zjava.model;

/**
 * Created by Adrian on 2017-06-06.
 */
public class Airline {
    int schemaVersion;
    String iata;
    String publicName;
    String icao;
    int nvls;

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

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public int getNvls() {
        return nvls;
    }

    public void setNvls(int nvls) {
        this.nvls = nvls;
    }
}
