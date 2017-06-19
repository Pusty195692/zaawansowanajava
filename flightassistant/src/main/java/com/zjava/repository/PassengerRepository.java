package com.zjava.repository;

import com.zjava.model.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Rafal Lebioda on 19.06.2017.
 */
@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Integer> {

    Optional<Passenger> findByPesel(String pesel);
}
