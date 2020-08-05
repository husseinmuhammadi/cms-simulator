package com.asan.cms.api;

import com.asan.cms.dto.Role;

public interface RoleService {
    Role findByName(String name);

    boolean exists(String name);
}
