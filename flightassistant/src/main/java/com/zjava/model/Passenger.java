package com.zjava.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Rafal Lebioda on 19.06.2017.
 */
@Data
@Entity
@Table(name = "PASSENGERS")
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 40)
    @Size(min = 0, max = 40)
    private String firstName;


    @Column(length = 40)
    @Size(min = 0, max = 40)
    private String lastName;


    @Column(length = 40, unique = true)
    @Size(min = 0, max = 40)
    private String pesel;
}
