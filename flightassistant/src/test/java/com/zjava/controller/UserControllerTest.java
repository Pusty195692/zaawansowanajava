package com.zjava.controller;

import com.zjava.controller.model.AccountActivation;
import com.zjava.controller.model.UserDTO;
import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.User;
import com.zjava.service.EmailService;
import com.zjava.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
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
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Piotr on 20.06.2017.
 */
@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserController userController;

    private Model model;

    @Before
    public void setUp() {
        userController = new UserController();
        MockitoAnnotations.initMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    public void getRegisterViewTest() {
        UserDTO userDTO = new UserDTO();

        final String resultVIew = userController.getRegisterView(userDTO, model);

        assertThat(resultVIew).isEqualTo(UserController.REGISTER_VIEW);
        final Map<String, Object> map = model.asMap();

        final UserDTO resultUser = (UserDTO) map.get("newUser");
        assertThat(resultUser).isNotEqualTo(null);
    }

    @Test
    public void sendRegisterMailUserExistsTest() throws EmailNotUniqueException {
        //given
        UserDTO userDTO = new UserDTO();
        User existingUser = new User();
        userDTO.setFirstName("Test");
        userDTO.setLastName("Test");
        userDTO.setEmail("example@example.com");
        userDTO.setPassword("password");
        userDTO.setPhoneNumber("+48608456789");

        BindingResult bindingResult = new DirectFieldBindingResult(userDTO, "newUser");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        when(userService.findUserByEmail(userDTO.getEmail())).thenReturn(existingUser);

        //when
        final String resultVIew = userController.sendRegisterMail(userDTO, bindingResult, model, redirectAttributes);
        assertThat(resultVIew).isEqualTo(UserController.REGISTER_VIEW);
        final Map<String, Object> map = model.asMap();

        final UserDTO resultUser = (UserDTO) map.get("newUser");
        //userDTO should be:
        assertThat(resultUser.getFirstName()).isEqualTo("Test");
        assertThat(resultUser.getLastName()).isEqualTo("Test");
        assertThat(resultUser.getEmail()).isEqualTo("example@example.com");
        assertThat(resultUser.getPassword()).isEqualTo("password");
        assertThat(resultUser.getPhoneNumber()).isEqualTo("+48608456789");
    }

    @Test
    public void sendRegisterMailUserDoNotExists() throws EmailNotUniqueException {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Test");
        userDTO.setLastName("Test");
        userDTO.setEmail("example@example.com");
        userDTO.setPassword("password");
        userDTO.setPhoneNumber("+48608456789");

        BindingResult bindingResult = new DirectFieldBindingResult(userDTO, "newUser");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        when(userService.findUserByEmail(userDTO.getEmail())).thenReturn(null);

        //when
        final String resultView = userController.sendRegisterMail(userDTO, bindingResult, model, redirectAttributes);

        //then
        assertThat(resultView).isEqualTo("redirect:/login");
    }

    @Test
    public void activateAccountTestTokenDoNotExists() throws EmailNotUniqueException {
        //given
        AccountActivation accountActivation = new AccountActivation();
        accountActivation.setToken(UUID.randomUUID().toString().replace("-",""));

        BindingResult bindingResult = new DirectFieldBindingResult(accountActivation, "accountActivation");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        when(userService.findUserByToken(accountActivation.getToken())).thenReturn(null);

        //when
        final String resultView = userController.activateAccount(accountActivation, bindingResult, model, redirectAttributes);

        //then
        assertThat(resultView).isEqualTo("redirect:/login");
    }

    @Test
    public void activateAccountTestTokenExists() throws EmailNotUniqueException {
        //given
        AccountActivation accountActivation = new AccountActivation();
        accountActivation.setToken(UUID.randomUUID().toString().replace("-",""));
        User existingUser = new User();

        BindingResult bindingResult = new DirectFieldBindingResult(accountActivation, "accountActivation");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        when(userService.findUserByToken(accountActivation.getToken())).thenReturn(existingUser);

        //when
        final String resultView = userController.activateAccount(accountActivation, bindingResult, model, redirectAttributes);

        //then
        assertThat(resultView).isEqualTo("redirect:/login");
    }



}
