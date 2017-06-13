package com.zjava.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@NoArgsConstructor
public class Destination {
    private String country;

    private Integer schemaVersion;

    private String iata;

    private String city;

    private HashMap<String, String> publicName;

}
