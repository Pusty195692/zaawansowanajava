package com.zjava.model.elements;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Integer schemaVersion;

    private String iata;

    private String publicName;

    private String icao;

    private Integer nvls;


}
