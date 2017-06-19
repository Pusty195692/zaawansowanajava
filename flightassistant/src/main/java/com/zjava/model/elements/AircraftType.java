package com.zjava.model.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "AIRCRAFTTYPES")
public class AircraftType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String longDescription;

    @Column
    private Integer schemaVersion;

    @Column
    private String shortDescription;

    @JsonProperty("iatamain")
    @Column
    private String iatamain;

    @Column
    private String iatasub;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!AircraftType.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final AircraftType other = (AircraftType) obj;


        if ((this.longDescription == null) ? (other.longDescription != null) : !this.longDescription.equals(other.longDescription)) {
            return false;
        }


        if (this.schemaVersion != other.schemaVersion) {
            return false;
        }

        if ((this.shortDescription == null) ? (other.shortDescription != null) : !this.shortDescription.equals(other.shortDescription)) {
            return false;
        }

        if ((this.iatamain == null) ? (other.iatamain != null) : !this.iatamain.equals(other.iatamain)) {
            return false;
        }

        if ((this.iatasub == null) ? (other.iatasub != null) : !this.iatasub.equals(other.iatasub)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longDescription, schemaVersion, shortDescription, iatamain, iatasub);
    }
}


