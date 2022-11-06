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
public class Cat {
    @Column()
    private String name;
    @Column()
    private String color;
    @Column()
    private String age;
}
