package com.zjava.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Adrian on 2017-06-06.
 */
@Data
@NoArgsConstructor
public class AircraftType {
    private String longDescription;

    private Integer schemaVersion;

    private String shortDescription;

    private Integer iatamain;

    private Integer iatasub;


}
