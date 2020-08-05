package com.asan.cms.to;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_COMPANY_NAME", columnNames = {"NAME"})
})
@SequenceGenerator(name = "SEQUENCE_GENERATOR", sequenceName = "COMPANY_SEQ")
public class Company extends EntityBase {

    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    @Column(name = "NAME_FA", length = 200)
    private String persianName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersianName() {
        return persianName;
    }

    public void setPersianName(String persianName) {
        this.persianName = persianName;
    }
}
