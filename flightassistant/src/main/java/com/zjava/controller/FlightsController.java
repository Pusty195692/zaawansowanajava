package com.zjava.controller;

import com.zjava.controller.model.ReservationFormModel;
import com.zjava.model.Passenger;
import com.zjava.model.elements.Flight;
import com.zjava.service.elements.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.inject.Inject;
import javax.validation.Valid;
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
    public String showReservationForm(@PathVariable("flightId") final Long id,Model model){
        ReservationFormModel viewModel=new ReservationFormModel();
        viewModel.setFlights(flighService.findAll());
        viewModel.setPassenger(new Passenger());

        if(id==0)
        {
            viewModel.setFlight(new Flight());
        }
        else
        {
            viewModel.setFlight(flighService.findFlightById(id));
        }
        model.addAttribute("viewModel",viewModel);
        return "reservationForm";
    }

    @PostMapping("/user/reservation/save")
    public String saveReservation(@Valid ReservationFormModel viewModel, BindingResult bindingResult, Model model) {
        if(viewModel!=null && viewModel.getFlight().getId() != null){
            Flight flight=flighService.findFlightById(viewModel.getFlight().getId());
            if(flight.containsPassenger(viewModel.getPassenger())==false){
                flight.addPassenger(viewModel.getPassenger());
                Flight temp=flighService.updateFlightPassengers(flight);
            }
            else {
                log.info("Taki pasażer już jest w danym locie");
            }
        }
        else{

        }
        return "redirect:/user/home";
    }
}
