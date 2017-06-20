package com.zjava.controller;

import com.zjava.controller.model.UserDTO;
import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.User;
import com.zjava.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Piotr on 20.06.2017.
 */
@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountController accountController;

    private Model model;

    @Before
    public void setUp() {
        accountController  = new AccountController();
        MockitoAnnotations.initMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    public void showLoggedUserAccountTest() {
        //given
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("example@example.com");
        user.setPassword("password");
        user.setPhoneNumber("+48608456789");

        //when
        String resultView = accountController.showLoggedUserAccount(model, user);

        //then
        assertThat(resultView).isEqualTo(AccountController.ACCOUNT_VIEW);
        final Map<String, Object> map = model.asMap();

        final User resultUser = (User) map.get("user");
        //user should be:
        assertThat(resultUser.getFirstName()).isEqualTo("Test");
        assertThat(resultUser.getLastName()).isEqualTo("Test");
        assertThat(resultUser.getEmail()).isEqualTo("example@example.com");
        assertThat(resultUser.getPassword()).isEqualTo("password");
        assertThat(resultUser.getPhoneNumber()).isEqualTo("+48608456789");
    }

    @Test
    public void updateAccountTest() throws EmailNotUniqueException {
        //given
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("example@example.com");
        user.setPassword("password");
        user.setPhoneNumber("+48608456789");

        User currentUser = new User();
        currentUser.setFirstName("Tester");
        currentUser.setLastName("Tester");
        currentUser.setEmail("example@example.com");
        currentUser.setPassword("password");
        currentUser.setPhoneNumber("+48608456789");

        BindingResult bindingResult = new DirectFieldBindingResult(user, "user");

        when(userService.updateUser(user)).thenThrow(new EmailNotUniqueException());

        //when
        String resultView = accountController.updateAccount(user, bindingResult, model, currentUser);

        //then
        assertThat(resultView).isEqualTo(AccountController.ACCOUNT_VIEW);
        final Map<String, Object> map = model.asMap();

        final User resultUser = (User) map.get("user");
        //user should be:
        assertThat(resultUser.getFirstName()).isEqualTo("Test");
        assertThat(resultUser.getLastName()).isEqualTo("Test");
        assertThat(resultUser.getEmail()).isEqualTo("example@example.com");
        assertThat(resultUser.getPassword()).isEqualTo("password");
        assertThat(resultUser.getPhoneNumber()).isEqualTo("+48608456789");
    }
}
