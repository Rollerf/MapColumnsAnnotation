package com.rollerf.mapcolumns.model;

import com.rollerf.mapcolumns.MapColumns;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@MapColumns
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Person {
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "AGE")
    private String age;

    private String address;
}
