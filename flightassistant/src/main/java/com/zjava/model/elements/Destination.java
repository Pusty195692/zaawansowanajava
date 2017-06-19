package com.zjava.model.elements;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "DESTINATIONS")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String country;

    @Column
    private Integer schemaVersion;

    @Column
    private String iata;

    @Column
    private String city;

    @Column
    private HashMap<String, String> publicName;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Destination.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Destination other = (Destination) obj;


        if ((this.country == null) ? (other.country != null) : !this.country.equals(other.country)) {
            return false;
        }

        if (this.schemaVersion != other.schemaVersion) {
            return false;
        }

        if ((this.iata == null) ? (other.iata != null) : !this.iata.equals(other.iata)) {
            return false;
        }

        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }

        if ((this.publicName == null) ? (other.publicName != null) : !this.publicName.equals(other.publicName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, schemaVersion, iata, city, publicName);
    }
    
}
