package com.zjava.controller;

import com.zjava.controller.FlightsController;
import com.zjava.model.elements.Flight;
import com.zjava.service.elements.FlightService;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Ada on 20.06.2017.
 */
@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class FlightsControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightsController flightsController;

    private Model model;

    @Before
    public void setUp() {
        flightsController = new FlightsController(flightService);
        MockitoAnnotations.initMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    public void showFlightsForUserTest() {
        //when
        final String resultView = flightsController.showFlightsForUser(model);

        //then
        assertThat(resultView).isEqualTo("flightsUser");

    }

    @Test
    public void showReservationFormTest(){
        //when
        final String resultView = flightsController.showReservationForm(Long.valueOf(0), model);

        //then
        assertThat(resultView).isEqualTo("reservationForm");
        final Map<String, Object> map = model.asMap();

        //resultFlight should be:
        final Flight resultFlight = (Flight) map.get("userEmail");
        assertThat(resultFlight).isEqualTo(null);

    }

    @Test
    public void showFlightsForAdminTest() {
        //when
        final String resultView = flightsController.showFlightsForAdmin(model);

        //then
        assertThat(resultView).isEqualTo("flightsAdmin");
    }

}
