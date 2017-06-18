package com.zjava.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.persistence.*;
//import org.springframework.data.annotation.Id;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "FLIGHTS")
public class Flight {
    @Id
    @Column
    private Integer id;
    @Column
    private String gate;
    @Column
    private String flightDirection;
    @Column
    private String expectedTimeBoarding;
    @Column
    private String getExpectedTimeGateClosing;
    @Column
    private Date scheduleDate;
    @Column
    private Integer airlineCode;
    @Column
    private Date estimatedLandingTime;
    /*@Column
    private String expectedTimeGateOpen;
    @Column
    private String mainFlight;

    @Column
    private Date actualLandingTime;
    @Column
    private String checkinAllocations;
    @Column
    private Date expectedTimeOnBelt;
*/
   /* @Column
    private String prefixIATA;
    @Column
    private AircraftType aircraftType;
    @Column
    private String serviceType;
    @Column
    private String scheduleTime;
    @Column
    private String flightName;
    @Column
    private String prefixICAO;
    /*
    @Column
    private String transferPositions;
    @Column
    private String codeshares;
    @Column
    private Integer schemaVersion;
    @Column
    private Integer terminal;
    @Column
    private Integer flightNumber;
    @Column
    private String publicEstimatedOffBlockTime;
    @Column
    private String aircraftRegistration;
    @Column
    private String baggageClaim;    //TODO
    @Column
    private String route;   //TODO

    @Column
    private String publicFlightState;   //TODO
    @Column
    private String actualOffBlockTime;

    */


}
