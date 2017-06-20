package com.zjava.controller.model;

import com.zjava.model.Passenger;
import com.zjava.model.elements.Flight;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Rafal Lebioda on 20.06.2017.
 */
@Data
@NoArgsConstructor
public class ReservationFormModel {
    private Passenger passenger;

    private Flight flight;

    private List<Flight> flights;
}
