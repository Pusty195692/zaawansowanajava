package com.zjava.model.elements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "FLIGHTS")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Flight aircraftType;

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

    @Column
    @JsonIgnore
    private String iatamain;

    @Column
    @JsonIgnore
    private String iatasub;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Flight.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Flight other = (Flight) obj;

        
        if ((this.flightName == null) ? (other.flightName != null) : !this.flightName.equals(other.flightName)) {
            return false;
        }

        if ((this.flightDirection == null) ? (other.flightDirection != null) : !this.flightDirection.equals(other.flightDirection)) {
            return false;
        }

        if (this.airlineCode != other.airlineCode) {
            return false;
        }

        if (this.terminal != other.terminal) {
            return false;
        }

        if (this.flightNumber != other.flightNumber) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(flightName, flightDirection, airlineCode, terminal, flightNumber);
    }
}
