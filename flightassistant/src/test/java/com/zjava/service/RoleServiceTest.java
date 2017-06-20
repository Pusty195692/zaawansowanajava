package com.zjava.service;

import com.zjava.model.Role;
import com.zjava.repository.RoleRepository;
import com.zjava.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rafal Lebioda on 20.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@PropertySource("classpath:application-test.properties ")
@Log4j2
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private Role role1;
    private Role role2;

    @Before
    public void setUp(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        role1=new Role();
        role2=new Role();
        role1.setAuthority(Role.Name.USER);
        role2.setAuthority(Role.Name.ADMIN);

        roleRepository.save(role1);
    }

    @Test
    public void addRoleTest(){
        roleService.addRole(role2);

        assertEquals("number of roles in repository is expected to be 2", 2, roleService.findAllRoles().size());
    }

    @After
    public void cleanUp() {
        roleRepository.deleteAll();
    }
}
