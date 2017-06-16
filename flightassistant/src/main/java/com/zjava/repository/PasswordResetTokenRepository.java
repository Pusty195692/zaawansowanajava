package com.zjava.repository;

import com.zjava.model.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Created by Piotr on 16.06.2017.
 */
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);

    @Transactional
    Long deleteByExpiryDateLessThan(LocalDateTime date);
}
