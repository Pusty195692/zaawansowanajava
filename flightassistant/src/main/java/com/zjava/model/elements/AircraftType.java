package com.zjava.model.elements;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("iatamain")
    private String iatamain;

    private String iatasub;


}
