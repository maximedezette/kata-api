package com.aperotech.kata.domain;

import java.util.Objects;

public class Kata  {
    private String name;

    public Kata() {
    }

    public Kata(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Kata) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Kata[" +
                "name=" + name + ']';
    }

}
