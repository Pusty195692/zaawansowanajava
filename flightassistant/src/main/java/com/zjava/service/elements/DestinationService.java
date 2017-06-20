package com.zjava.service.elements;

import com.zjava.model.elements.Destination;
import com.zjava.repository.elements.DestinationRepository;
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
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Value("${port:8080}")
    private String port;

    @Value("${host:localhost}")
    private String host;

    @Value("${protocol:http}")
    private String protocol;

    public Destination save(Destination destination) {
        return destinationRepository.save(destination);
    }

    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    public long count() {
        return destinationRepository.count();
    }

    public void deleteAll() {destinationRepository.deleteAll();}
}
