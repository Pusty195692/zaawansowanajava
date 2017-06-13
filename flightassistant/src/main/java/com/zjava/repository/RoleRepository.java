package com.zjava.repository;

import com.zjava.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Created by Rafal Lebioda on 13.06.2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByAuthorityIgnoreCase(String authority);

}
