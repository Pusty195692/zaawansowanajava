package com.zjava.model.elements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zjava.model.Passenger;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "FLIGHTS")
public class Flight {

    @Id
    private Long id;

    @Column
    private String prefixIATA;

    @JsonProperty("serviceType")
    @Column
    private String serviceType;

    @Column
    private String scheduleTime;

    @Column
    private String flightName;

    @Column
    private String prefixICAO;

    @Column
    private String expectedTimeBoarding;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private AircraftType aircraftType;

    @Column
    private String getExpectedTimeGateClosing;

    @Column
    private Date scheduleDate;

    @Column
    private String flightDirection;

    @Column
    private Integer airlineCode;

    @Column
    private String mainFlight;

    @Column
    @JsonIgnore
    private String transferPositions;

    @Column
    @JsonIgnore
    private String codeshares;

    @Column
    private Date estimatedLandingTime;

    @Column
    private Integer schemaVersion;

    @Column
    private Date actualLandingTime;

    @Column
    private Integer terminal;

    @Column
    @JsonIgnore
    private String checkinAllocations;

    @Column
    private Integer flightNumber;

    @Column
    private String publicEstimatedOffBlockTime;

    @Column
    private String aircraftRegistration;

    @Column
    @JsonIgnore
    private String baggageClaim;

    @Column
    @JsonIgnore
    private String route;

    @Column
    private Date expectedTimeOnBelt;

    @Column
    @JsonIgnore
    private String publicFlightState;

    @Column
    private String actualOffBlockTime;

    @Column
    private String gate;

    @Column
    private String expectedTimeGateOpen;

    @Column
    private String expectedTimeGateClosing;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    private Set<Passenger> passengers = new HashSet<>();

    public boolean containsPassenger(Passenger passenger) {
        return passengers.contains(passenger);
    }

    public boolean addPassenger(Passenger passenger) {
        return passengers.add(passenger);
    }

}
