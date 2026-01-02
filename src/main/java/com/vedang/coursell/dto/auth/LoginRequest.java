package com.vedang.coursell.dto.auth;

public record LoginRequest(
        String email,
        String password
) {}

