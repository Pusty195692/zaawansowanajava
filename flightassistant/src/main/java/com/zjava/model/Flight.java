package com.zjava.model;

import java.util.Date;

/**
 * Created by Adrian on 2017-06-06.
 */
public class Flight {
    String prefixIATA;
    String serviceType;
    String scheduleTime;
    String flightName;
    String prefixICAO;
    String expectedTimeBoarding;
    AircraftType aircraftType;
    String getExpectedTimeGateClosing;
    Date scheduleDate;
    String flightDirection;
    int airlineCode;
    int id;
    String mainFlight;
    String transferPositions;
    String codeshares;
    Date estimatedLandingTime;
    int schemaVersion;
    Date actualLandingTime;
    int terminal;
    String checkinAllocations;
    int flightNumber;
    String publicEstimatedOffBlockTime;
    String aircraftRegistration;
    String baggageClaim;    //TODO
    String route;   //TODO
    Date expectedTimeOnBelt;
    String publicFlightState;   //TODO
    String actualOffBlockTime;
    String gate;
    String expectedTimeGateOpen;

    public String getPrefixIATA() {
        return prefixIATA;
    }

    public void setPrefixIATA(String prefixIATA) {
        this.prefixIATA = prefixIATA;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getPrefixICAO() {
        return prefixICAO;
    }

    public void setPrefixICAO(String prefixICAO) {
        this.prefixICAO = prefixICAO;
    }

    public String getExpectedTimeBoarding() {
        return expectedTimeBoarding;
    }

    public void setExpectedTimeBoarding(String expectedTimeBoarding) {
        this.expectedTimeBoarding = expectedTimeBoarding;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getGetExpectedTimeGateClosing() {
        return getExpectedTimeGateClosing;
    }

    public void setGetExpectedTimeGateClosing(String getExpectedTimeGateClosing) {
        this.getExpectedTimeGateClosing = getExpectedTimeGateClosing;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getFlightDirection() {
        return flightDirection;
    }

    public void setFlightDirection(String flightDirection) {
        this.flightDirection = flightDirection;
    }

    public int getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(int airlineCode) {
        this.airlineCode = airlineCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainFlight() {
        return mainFlight;
    }

    public void setMainFlight(String mainFlight) {
        this.mainFlight = mainFlight;
    }

    public String getTransferPositions() {
        return transferPositions;
    }

    public void setTransferPositions(String transferPositions) {
        this.transferPositions = transferPositions;
    }

    public String getCodeshares() {
        return codeshares;
    }

    public void setCodeshares(String codeshares) {
        this.codeshares = codeshares;
    }

    public Date getEstimatedLandingTime() {
        return estimatedLandingTime;
    }

    public void setEstimatedLandingTime(Date estimatedLandingTime) {
        this.estimatedLandingTime = estimatedLandingTime;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public Date getActualLandingTime() {
        return actualLandingTime;
    }

    public void setActualLandingTime(Date actualLandingTime) {
        this.actualLandingTime = actualLandingTime;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public String getCheckinAllocations() {
        return checkinAllocations;
    }

    public void setCheckinAllocations(String checkinAllocations) {
        this.checkinAllocations = checkinAllocations;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPublicEstimatedOffBlockTime() {
        return publicEstimatedOffBlockTime;
    }

    public void setPublicEstimatedOffBlockTime(String publicEstimatedOffBlockTime) {
        this.publicEstimatedOffBlockTime = publicEstimatedOffBlockTime;
    }

    public String getAircraftRegistration() {
        return aircraftRegistration;
    }

    public void setAircraftRegistration(String aircraftRegistration) {
        this.aircraftRegistration = aircraftRegistration;
    }

    public String getBaggageClaim() {
        return baggageClaim;
    }

    public void setBaggageClaim(String baggageClaim) {
        this.baggageClaim = baggageClaim;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getExpectedTimeOnBelt() {
        return expectedTimeOnBelt;
    }

    public void setExpectedTimeOnBelt(Date expectedTimeOnBelt) {
        this.expectedTimeOnBelt = expectedTimeOnBelt;
    }

    public String getPublicFlightState() {
        return publicFlightState;
    }

    public void setPublicFlightState(String publicFlightState) {
        this.publicFlightState = publicFlightState;
    }

    public String getActualOffBlockTime() {
        return actualOffBlockTime;
    }

    public void setActualOffBlockTime(String actualOffBlockTime) {
        this.actualOffBlockTime = actualOffBlockTime;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getExpectedTimeGateOpen() {
        return expectedTimeGateOpen;
    }

    public void setExpectedTimeGateOpen(String expectedTimeGateOpen) {
        this.expectedTimeGateOpen = expectedTimeGateOpen;
    }
}
