package com.aperotech.kata.domain;

public record Kata(String name) {

    public String getName() {
        return name;
    }
}
