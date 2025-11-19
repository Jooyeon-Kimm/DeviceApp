package com.example.deviceapp.dto.log;

import com.example.deviceapp.entity.LogLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DeviceLogRequest(
        @NotNull
        LogLevel logLevel,

        @NotBlank
        @Size(max = 500)
        String message
) {}