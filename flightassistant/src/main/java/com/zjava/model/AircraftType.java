package com.zjava.model;

/**
 * Created by Adrian on 2017-06-06.
 */
public class AircraftType {
    String longDescription;
    int schemaVersion;
    String shortDescription;
    int iatamain;
    int iatasub;

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getIatamain() {
        return iatamain;
    }

    public void setIatamain(int iatamain) {
        this.iatamain = iatamain;
    }

    public int getIatasub() {
        return iatasub;
    }

    public void setIatasub(int iatasub) {
        this.iatasub = iatasub;
    }
}
