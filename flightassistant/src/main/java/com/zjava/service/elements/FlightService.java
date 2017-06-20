package com.zjava.service.elements;

import com.zjava.model.elements.Flight;
import com.zjava.repository.elements.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Adrian on 2017-06-18.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class FlightService {

    private final FlightRepository flightRepository;

    @Value("${port:8080}")
    private String port;

    @Value("${host:localhost}")
    private String host;

    @Value("${protocol:http}")
    private String protocol;

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public long count() {
        return flightRepository.count();
    }

    public void deleteAll() {flightRepository.deleteAll();}

    public Flight findFlightById(Long id){
        log.info("Searching for flight with id[" + id + "]");

        return flightRepository.findOne(id);
    }

    public Flight updateFlightPassengers(Flight flight){
        log.info("Updating flight: " + flight);
        Flight flightToUpdate = flightRepository.findOne(flight.getId());
        flightToUpdate.setPassengers(flight.getPassengers());
        flightToUpdate=flightRepository.save(flightToUpdate);
        return flightToUpdate;
    }
}
