package com.zjava.controller;

import com.zjava.controller.model.ResetPassword;
import com.zjava.controller.model.UserEmail;
import com.zjava.model.User;
import com.zjava.service.EmailService;
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

import java.net.MalformedURLException;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Piotr on 17.06.2017.
 */
@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class ResetPasswordControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ResetPasswordController passwordController;

    private Model model;

    @Before
    public void setUp() {
        passwordController = new ResetPasswordController();
        MockitoAnnotations.initMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    public void getForgetPasswordViewTest() {
        //given
        UserEmail userEmail = new UserEmail();

        //when
        final String resultView = passwordController.getForgetPasswordView(userEmail, model);

        //then
        assertThat(resultView).isEqualTo(ResetPasswordController.FORGET_VIEW);
        final Map<String, Object> map = model.asMap();

        //resultEmail should be:
        final UserEmail resultEmail = (UserEmail) map.get("userEmail");
        assertThat(resultEmail.getUserMail()).isEqualTo(null);
    }

    @Test
    public void sendEmailUserDoNotExists() throws MalformedURLException {
        //given
        UserEmail userEmail = new UserEmail();
        BindingResult bindingResult = new DirectFieldBindingResult(userEmail, "userEmail");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        userEmail.setUserMail("example@example.com");
        when(userService.findUserByEmail(userEmail.getUserMail())).thenReturn(null);

        //when
        final String resultView = passwordController.sendEmail(userEmail, bindingResult, model, redirectAttributes);

        //then
        assertThat(resultView).isEqualTo(ResetPasswordController.REDIRECT_FORGET_VIEW);
        final Map<String, Object> map = model.asMap();

        final UserEmail resultEmail = (UserEmail) map.get("userEmail");
        //result email should be:
        assertThat(resultEmail.getUserMail()).isEqualTo("example@example.com");

    }

    @Test
    public void sendEmailUserExists() throws MalformedURLException {
        //given
        UserEmail userEmail = new UserEmail();
        userEmail.setUserMail("example@example.com");
        BindingResult bindingResult = new DirectFieldBindingResult(userEmail, "userEmail");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("example@example.com");
        user.setPassword("password");
        user.setPhoneNumber("+48608456789");
        when(userService.findUserByEmail(userEmail.getUserMail())).thenReturn(user);

        //when
        final String resultView = passwordController.sendEmail(userEmail, bindingResult, model, redirectAttributes);

        //then
        assertThat(resultView).isEqualTo("redirect:/login");
    }

    @Test
    public void changePasswordTokenInvalidTest() {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setUserMail("example@example.com");
        resetPassword.setResetToken(UUID.randomUUID().toString().replace("-",""));
        resetPassword.setNewPassword("noweHaslo");
        BindingResult bindingResult = new DirectFieldBindingResult(resetPassword, "resetPassword");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("example@example.com");
        user.setPassword("password");
        user.setPhoneNumber("+48608456789");
        when(userService.findUserByEmail(resetPassword.getUserMail())).thenReturn(user);
        when(userService.validatePasswordResetToken(resetPassword.getResetToken(), resetPassword.getUserMail())).thenReturn(false);

        final String resultView = passwordController.changePassword(resetPassword, bindingResult, model, redirectAttributes);
        assertThat(resultView).isEqualTo("redirect:/login");
    }

    @Test
    public void changePasswordTokenValidTest() {
        //given
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setUserMail("example@example.com");
        resetPassword.setNewPassword("noweHaslo");
        BindingResult bindingResult = new DirectFieldBindingResult(resetPassword, "resetPassword");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("example@example.com");
        user.setPassword("password");
        user.setPhoneNumber("+48608456789");
        when(userService.findUserByEmail(resetPassword.getUserMail())).thenReturn(user);
        when(userService.validatePasswordResetToken(resetPassword.getResetToken(), resetPassword.getUserMail())).thenReturn(true);

        //when
        final String resultView = passwordController.changePassword(resetPassword, bindingResult, model, redirectAttributes);

        //then
        assertThat(resultView).isEqualTo("redirect:/login");
    }



}
