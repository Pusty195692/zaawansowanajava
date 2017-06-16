package com.zjava.model.elements;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@NoArgsConstructor
public class Airline {
    private Integer schemaVersion;

    private String iata;

    private String publicName;

    private String icao;

    private Integer nvls;


}
