package com.zjava.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@NoArgsConstructor
public class Flight {
    private String prefixIATA;
    private String serviceType;
    private String scheduleTime;
    private String flightName;
    private String prefixICAO;
    private String expectedTimeBoarding;
    private AircraftType aircraftType;
    private String getExpectedTimeGateClosing;
    private Date scheduleDate;
    private String flightDirection;
    private Integer airlineCode;
    private Integer id;
    private String mainFlight;
    private String transferPositions;
    private String codeshares;
    private Date estimatedLandingTime;
    private Integer schemaVersion;
    private Date actualLandingTime;
    private Integer terminal;
    private String checkinAllocations;
    private Integer flightNumber;
    private String publicEstimatedOffBlockTime;
    private String aircraftRegistration;
    private String baggageClaim;    //TODO
    private String route;   //TODO
    private Date expectedTimeOnBelt;
    private String publicFlightState;   //TODO
    private String actualOffBlockTime;
    private String gate;
    private String expectedTimeGateOpen;


}
