package com.ainder.ainder.entities;
import javax.persistence.*;

@Entity
@Table(name = "T_ROLE", schema = "GRZYBOW1")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "ID_ROLE", nullable = false, precision = 0)
    private Long id_role;

    @Basic
    @Column(name = "ROLE", nullable = false, length = 15)
    String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id_role ^ (id_role >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}