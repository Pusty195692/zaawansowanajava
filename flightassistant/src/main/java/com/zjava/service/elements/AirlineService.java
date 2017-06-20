package com.zjava.service.elements;

import com.zjava.model.elements.Airline;
import com.zjava.repository.elements.AirlineRepository;
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
public class AirlineService {

    private final AirlineRepository airlineRepository;

    @Value("${port:8080}")
    private String port;

    @Value("${host:localhost}")
    private String host;

    @Value("${protocol:http}")
    private String protocol;

    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    public long count() {
        return airlineRepository.count();
    }

    public void deleteAll() {airlineRepository.deleteAll();}
}
