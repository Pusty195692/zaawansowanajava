package com.zjava.repository.elements;

import com.zjava.model.elements.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adrian on 2017-06-18.
 */
@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {
    Destination save(Destination flight);

    List<Destination> findAll();

    Destination findOne(Long id);

    boolean exists(Long id);

    List<Destination> save(List<Destination> entities);

    List<Destination> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(List<Destination> flights);

    void deleteAll();
}