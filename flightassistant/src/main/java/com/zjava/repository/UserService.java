package com.zjava.repository;

import com.zjava.exception.EmailNotUniqueException;
import com.zjava.model.Role;
import com.zjava.model.User;
import com.zjava.repository.RoleRepository;
import com.zjava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.inject.Inject;


/**
 * Created by Rafal Lebioda on 13.06.2017.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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

    private User trySaveUser(User user, boolean isNewUser, boolean isCurrentAccount) throws EmailNotUniqueException {
        try {
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


}
