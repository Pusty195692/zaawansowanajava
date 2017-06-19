package com.zjava.controller;

import com.zjava.model.Passenger;
import com.zjava.model.elements.Flight;
import com.zjava.service.elements.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Rafal Lebioda on 19.06.2017.
 */
@Log4j2
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class FlightsController {

    private final FlightService flighService;

    @GetMapping("/user/flights")
    public String showFlightsForUser(Model model){
        List<Flight> allFlights = flighService.findAll();
        model.addAttribute("flights", allFlights);
        return "flightsUser";
    }

    @GetMapping("/user/reservation/{flightId}")
    public String showReservationForm(@PathVariable("flightId") final Integer id,Model model){
        model.addAttribute("flights", flighService.findAll());
        model.addAttribute("passenger",new Passenger());
        Flight flight=new Flight();
        if(id==0)
        {
            model.addAttribute("flight",new Flight());
        }
        else
        {
            model.addAttribute("flight",new Flight()); //TO DO
        }
        return "reservationForm";
    }
}
