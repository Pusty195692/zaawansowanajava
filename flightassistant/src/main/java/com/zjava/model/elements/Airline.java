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

}
