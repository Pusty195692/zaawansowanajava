package com.zjava.repository.elements;

import com.zjava.model.elements.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Created by Adrian on 2017-06-18.
 */
@Repository
public interface AirlineRepository extends CrudRepository<Airline, Long> {
    Airline save(Airline flight);

    @ModelAttribute("airlines")
    List<Airline> findAll();

    Airline findOne(Long id);

    boolean exists(Long id);

    long count();

    void delete(Long id);

    void deleteAll();
}