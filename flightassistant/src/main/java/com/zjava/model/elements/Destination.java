package com.zjava.model.elements;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;

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

}
