package com.zjava.service;

import com.zjava.model.Passenger;
import com.zjava.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Rafal Lebioda on 19.06.2017.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public List<Passenger> findAllPassenger() {
        log.info("Searching all passengers");

        return StreamSupport.stream(passengerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
