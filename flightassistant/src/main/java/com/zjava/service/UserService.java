package com.zjava.service;

import com.zjava.controller.model.UserDTO;
import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.PasswordResetToken;
import com.zjava.model.Role;
import com.zjava.model.User;
import com.zjava.repository.PasswordResetTokenRepository;
import com.zjava.repository.RoleRepository;
import com.zjava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.zjava.service.EmailService.Message;

/**
 * Created by Rafal Lebioda on 13.06.2017.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordResetTokenRepository tokenRepository;

    @Value("${port:8080}")
    private String port;

    @Value("${host:localhost}")
    private String host;

    @Value("${protocol:http}")
    private String protocol;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MapperFacade orikaMapper;

    public List<User> findAllUsers() {
        log.info("Searching all users");

        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public User loadUserByUsername(String email) {
        log.info("loadUserByUsername method from UserService invoked");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
    }

    public User findUserByEmail(String email) {
        log.info("findUserByEmail method from UserService invoked");
        return userRepository.findByEmail(email).orElse(null);
    }

    public User addUser(User user) throws EmailNotUniqueException {
        log.info("Adding user: " + user);
        addDefaultRoleIfNeeded(user);
        return trySaveUser(user, true, false);
    }

    private void addDefaultRoleIfNeeded(User user) {
        log.info("setUserRoleIfNoneGranted method invoked for user: " + user);
        if (CollectionUtils.isEmpty(user.getAuthorities())) {
            roleRepository.findByAuthorityIgnoreCase(Role.Name.USER).ifPresent(role -> user.getRoles().add(role));
        }
    }

    public UserDTO addUser(UserDTO user) throws EmailNotUniqueException, SecurityException {
        User userToUpdateOrAdd = orikaMapper.map(user, User.class);
        return orikaMapper.map(addUser(userToUpdateOrAdd), UserDTO.class);
    }

    private User trySaveUser(User user, boolean isNewUser, boolean isCurrentAccount) throws EmailNotUniqueException {
        try {
            user.setPassword(passwordEncoder.encode((user.getPassword())));
            User userToSave = userRepository.save(user);

//            if (isNewUser) {
//                String token = generateToken();
//                createPasswordResetTokenForUser(userToSave, token);
//                emailService.sendEmail(constructResetTokenEmail(user, token));
//            }
//
//            if (isCurrentAccount) {
//                Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }

            return userToSave;

        } catch (DataIntegrityViolationException e) {
            throw new EmailNotUniqueException(e);
        }
    }

    ////////methods fo password reset

    public String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(token);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));
        tokenRepository.save(resetToken);
    }

    public boolean validatePasswordResetToken(String token, String email) {
        log.info("validatePasswordResetToken method invoke");
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null) {
            log.info("Token is invalid");
            return false;
        }

        User user = resetToken.getUser();
        if (!user.getEmail().equals(email)) {
            log.info("Token is invalid");
            return false;
        }

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            log.warn("Token[" + resetToken + "] expired - removing");
            tokenRepository.delete(resetToken);
            return false;
        }

        tokenRepository.delete(resetToken);

        log.info("Token " + token + " is valid");
        log.info("Token " + token + " removed from database");

        return true;
    }

    public void changePassword(User user, String password) {
        User userToUpdate = userRepository.findOne(user.getId());
        userToUpdate.setPassword(passwordEncoder.encode((password)));
        userRepository.save(userToUpdate);
        log.info("User " + user.getUsername() + " changed password for " + password);
    }

    public SimpleMailMessage constructResetTokenEmail(User user, String token) {
        List<String> to = new ArrayList<>();
        to.add(user.getEmail());
        String url = protocol + "://" + host + ":" + port + "/account/password/change/reset/token/" + token;
        Message message = new Message("flightassistant2017@gmail.com", to, "Reset password", url);
        return message.constructEmail();
    }

    public SimpleMailMessage constructConfirmationEmail(UserDTO userDTO, String token) {
        List<String> to = new ArrayList<>();
        to.add(userDTO.getEmail());
        String url = protocol + "://" + host + ":" + port + "/account/register/confirm/token/" + token;
        Message message = new Message("flightassistant2017@gmail.com", to, "Confirm email", url);
        return message.constructEmail();
    }



}
