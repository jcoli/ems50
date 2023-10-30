package com.tecnocoli.ems50.device;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeferson Coli on 10/26/23.
 * Tecnocoli
 * jcoli@tecnocoli.com.br
 */
public class Training implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private List<SubTraining> subTrainings = new ArrayList<>();

    public Training() {
    }

    public Training(Integer id, String name, String description, List<SubTraining> subTrainings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subTrainings = subTrainings;
    }

    public Training(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return id.equals(training.id) && name.equals(training.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
