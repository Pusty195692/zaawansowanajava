package com.zjava.model.elements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("serviceType")
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
    private Long id;
    private String mainFlight;
    @JsonIgnore
    private String transferPositions;
    @JsonIgnore
    private String codeshares;
    private Date estimatedLandingTime;
    private Integer schemaVersion;
    private Date actualLandingTime;
    private Integer terminal;
    @JsonIgnore
    private String checkinAllocations;
    private Integer flightNumber;
    private String publicEstimatedOffBlockTime;
    private String aircraftRegistration;
    @JsonIgnore
    private String baggageClaim;
    @JsonIgnore
    private String route;
    private Date expectedTimeOnBelt;
    @JsonIgnore
    private String publicFlightState;
    private String actualOffBlockTime;
    private String gate;
    private String expectedTimeGateOpen;
    private String expectedTimeGateClosing;

}
