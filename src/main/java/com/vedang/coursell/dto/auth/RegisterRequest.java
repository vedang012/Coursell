package com.vedang.coursell.dto.auth;

import com.vedang.coursell.model.Role;

public record RegisterRequest(
        String name,
        String email,
        String password,
        Role role
) {}

