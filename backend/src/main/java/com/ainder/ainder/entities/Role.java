package com.ainder.ainder.entities;
import javax.persistence.*;

@Entity
@Table(name = "T_ROLE")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "ID_ROLE", nullable = false, precision = 0)
    private Long id_role;

    @Basic
    @Column(name = "ROLE", nullable = false, length = 15)
    String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id_role = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id_role;
    }

    public void setId(Long id) {
        this.id_role = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}