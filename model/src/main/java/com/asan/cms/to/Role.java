package com.asan.cms.to;

import javax.persistence.*;

@Entity
@Table(name = "ROLE", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ROLE_NAME", columnNames = {"NAME"})
})
@SequenceGenerator(name = "SEQUENCE_GENERATOR", sequenceName = "ROLE_SEQ")
public class Role extends EntityBase {

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Column(name = "name", length = 200)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
