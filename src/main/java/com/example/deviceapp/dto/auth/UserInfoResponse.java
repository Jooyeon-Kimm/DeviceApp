package com.example.deviceapp.dto.auth;

public record UserInfoResponse(
        Long id,
        String username,
        String role
) {}