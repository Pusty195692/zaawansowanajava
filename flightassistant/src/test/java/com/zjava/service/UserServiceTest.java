package com.zjava.service;

import com.zjava.model.User;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Rafal Lebioda on 16.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@PropertySource("classpath:application-test.properties ")
@Log4j2
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp(){
        userRepository.deleteAll();
        user1=new User();
        user2=new User();
        user3=new User();
        user3.setFirstName("user");
        user3.setEmail("user@user.com");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    public void findAllUsersTest(){
        // preparation
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        List<User> usersFromRepository;

        // act
        usersFromRepository = userService.findAllUsers();

        // assert
        assertEquals("Size of returned list will be the same as the size of prepared list of users", users.size(), usersFromRepository.size());
    }

    @Test
    public void addUserTest(){
        try {
            User newUser=new User();
            newUser.setPassword("$2a$12$jsm4Mbse40VEL/FlMHwk3OyHry0WZ2sZKZkIk92DnZTGoQfzLh1Pq");
            userService.addUser(newUser);
        }
        catch (Exception ex){}
        assertEquals("number of users in repository is expected to be 3", 3, userService.findAllUsers().size());
    }

   @Test
   public void findUserByIdTest(){
       // act
       userRepository.save(user3);

       // assert
       assertNotNull("Null will not be returned", userService.findUserById(user3.getId()));
       assertEquals("Returned user will be 'user'", "user", userService.findUserById(user3.getId()).getFirstName());
   }

   @Test
   public void findUserByEmail(){
       userRepository.save(user3);

       assertEquals("Returned user will be 'user'", "user", userService.findUserByEmail("user@user.com").getFirstName());
   }


    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }
}
