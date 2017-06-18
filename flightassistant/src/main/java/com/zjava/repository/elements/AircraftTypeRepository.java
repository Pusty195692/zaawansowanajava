package com.zjava.repository.elements;

import com.zjava.model.elements.AircraftType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adrian on 2017-06-18.
 */
@Repository
public interface AircraftTypeRepository extends CrudRepository<AircraftType, Long> {
    AircraftType save(AircraftType flight);

    List<AircraftType> findAll();

    AircraftType findOne(Long id);

    boolean exists(Long id);

    List<AircraftType> save(List<AircraftType> entities);

    List<AircraftType> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(List<AircraftType> flights);

    void deleteAll();
}