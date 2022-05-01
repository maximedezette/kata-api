package com.aperotech.kata.data;

import com.aperotech.kata.domain.Kata;

import javax.persistence.*;

@Entity(name = "Kata")
public class KataEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Kata toDomainKata(){
        return new Kata(this.name);
    }
}
