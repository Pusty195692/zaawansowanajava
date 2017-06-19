package com.zjava.model.elements;

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
@Table(name = "AIRLINES")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Integer schemaVersion;

    @Column
    private String iata;

    @Column
    private String publicName;

    @Column
    private String icao;

    @Column
    private Integer nvls;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Airline.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Airline other = (Airline) obj;

        if (this.schemaVersion != other.schemaVersion) {
            return false;
        }

        if ((this.iata == null) ? (other.iata != null) : !this.iata.equals(other.iata)) {
            return false;
        }

        if ((this.publicName == null) ? (other.publicName != null) : !this.publicName.equals(other.publicName)) {
            return false;
        }

        if ((this.icao == null) ? (other.icao != null) : !this.icao.equals(other.icao)) {
            return false;
        }

        if (this.nvls != other.nvls) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemaVersion, iata, publicName, icao, nvls);
    }
}
