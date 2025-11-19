package com.example.deviceapp.dto.log;

import com.example.deviceapp.entity.LogLevel;

import java.time.LocalDateTime;

public record DeviceLogResponse(
        Long id,
        Long deviceId,
        LogLevel logLevel,
        String message,
        LocalDateTime createdAt
) {}