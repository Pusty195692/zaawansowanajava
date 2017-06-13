package com.zjava.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Rafal Lebioda on 13.06.2017.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
    public interface Name {
        String ADMIN = "ROLE_ADMIN";
        String USER = "ROLE_USER";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String authority;

    public Role(String roleName) {
        authority = roleName;
    }
}
