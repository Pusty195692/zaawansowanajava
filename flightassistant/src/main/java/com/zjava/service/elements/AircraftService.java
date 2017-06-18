package com.zjava.service.elements;

import com.zjava.model.elements.AircraftType;
import com.zjava.repository.elements.AircraftTypeRepository;
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
public class AircraftService {

    private final AircraftTypeRepository aircraftTypeRepository;

    @Value("${port:8080}")
    private String port;

    @Value("${host:localhost}")
    private String host;

    @Value("${protocol:http}")
    private String protocol;

    public AircraftType save(AircraftType aircraftType) {
        return aircraftTypeRepository.save(aircraftType);
    }

    public List<AircraftType> findAll() {
        return aircraftTypeRepository.findAll();
    }

    public long count() {
        return aircraftTypeRepository.count();
    }
}
