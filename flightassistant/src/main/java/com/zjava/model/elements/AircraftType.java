package com.zjava.model.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


}
