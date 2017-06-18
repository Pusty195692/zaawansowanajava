package com.zjava.service;

import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.PasswordResetToken;
import com.zjava.model.Role;
import com.zjava.model.User;
import com.zjava.model.Flight;
import com.zjava.repository.PasswordResetTokenRepository;
import com.zjava.repository.RoleRepository;
import com.zjava.repository.UserRepository;
import com.zjava.repository.FlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.zjava.service.EmailService.Message;

/**
 * Created by Ada Stachowiak on 17.06.2017.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class FlightsService {

    @Autowired
    private final FlightsRepository flightsRepository;

    private List<Flight> flightList;

    public List<Flight> findAllFlights(){
        log.info("Searching all flights");

        //return flightsRepository.findAll();
        flightList = flightsRepository.findAll();
        return flightList;
       // return StreamSupport.stream(flightsRepository.findAll().spliterator(), false)
                //.collect(Collectors.toList());
    }
/*
    public void addFlight(Flight flight){
        this.flightsRepository.addFlight(flight);
    }*/


}
