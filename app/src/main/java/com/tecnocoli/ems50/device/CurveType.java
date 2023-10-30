package com.tecnocoli.ems50.device;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Jeferson Coli on 10/26/23.
 * Tecnocoli
 * jcoli@tecnocoli.com.br
 */
public class CurveType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String type;

    public CurveType() {
    }

    public CurveType(Integer id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public CurveType(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CurveType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurveType curveType = (CurveType) o;
        return id.equals(curveType.id) && name.equals(curveType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
