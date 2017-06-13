package com.zjava.repository;

import com.zjava.model.Role;
import com.zjava.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Rafal Lebioda on 13.06.2017.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class RoleService {

    private final RoleRepository roleRepository;

    public Role addRole(Role role) {
        log.info("Adding role " + role);
        return roleRepository.save(role);
    }

    public Optional<Role> findRoleByName(final String roleName) {
        log.info("Searching for role: " + roleName);
        return roleRepository.findByAuthorityIgnoreCase(roleName);
    }

    public List<Role> findAllRoles() {
        log.info("Searching all roles");
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
