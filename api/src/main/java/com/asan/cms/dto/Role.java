package com.asan.cms.dto;

public class Role extends DtoBase {

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
