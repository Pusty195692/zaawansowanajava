package com.zjava.controller;

import com.zjava.model.Role;
import com.zjava.model.User;
import com.zjava.model.Flight;
import com.zjava.service.FlightsService;
import com.zjava.repository.FlightsRepository;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ada Stachowiak on 17.06.2017.
 */

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class FlightsController {

    @Autowired
    private final FlightsService flightsService;
/*
    @Autowired
    public FlightsController(FlightsService flightsService){
        this.flightsService = flightsService;
    }*/

    @RequestMapping("/flights")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView("flights");
        mav.addObject("flights", flightsService.findAllFlights());
        return mav;
    }


}
