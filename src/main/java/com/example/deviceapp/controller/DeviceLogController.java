package com.example.deviceapp.controller;

import com.example.deviceapp.dto.log.DeviceLogRequest;
import com.example.deviceapp.dto.log.DeviceLogResponse;
import com.example.deviceapp.service.DeviceLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/devices/{deviceId}/logs")
@RequiredArgsConstructor
public class DeviceLogController {

    private final DeviceLogService deviceLogService;

    @PostMapping
    public DeviceLogResponse addLog(@PathVariable Long deviceId,
                                    @RequestBody @Valid DeviceLogRequest request) {
        return deviceLogService.addLog(deviceId, request);
    }

    @GetMapping
    public List<DeviceLogResponse> getLogs(
            @PathVariable Long deviceId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to
    ) {
        return deviceLogService.getLogs(deviceId, from, to);
    }
}