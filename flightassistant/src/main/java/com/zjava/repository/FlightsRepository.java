package com.zjava.repository;

import com.zjava.model.User;
import com.zjava.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * Created by Ada Stachowiak on 17.06.2017.
 */
@Repository
public interface FlightsRepository extends CrudRepository<Flight, Integer> { //integer?

    public List<Flight> findAll();
    //public void addFlight(Flight flight);

}
