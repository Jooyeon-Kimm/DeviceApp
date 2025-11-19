package com.example.deviceapp.dto.device;

import com.example.deviceapp.entity.DeviceStatus;

import java.time.LocalDateTime;

public record DeviceResponse(
        Long id,
        String name,
        String serialNumber,
        DeviceStatus status,
        LocalDateTime lastUpdatedAt
) {}