package com.zjava.repository.elements;

import com.zjava.model.elements.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adrian on 2017-06-18.
 */
@Repository
public interface AirlineRepository extends CrudRepository<Airline, Long> {
    Airline save(Airline flight);

    List<Airline> findAll();

    Airline findOne(Long id);

    boolean exists(Long id);

    List<Airline> save(List<Airline> entities);

    List<Airline> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(List<Airline> flights);

    void deleteAll();
}