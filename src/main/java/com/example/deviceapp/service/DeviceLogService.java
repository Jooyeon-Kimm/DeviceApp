package com.example.deviceapp.service;

import com.example.deviceapp.dto.log.DeviceLogRequest;
import com.example.deviceapp.dto.log.DeviceLogResponse;
import com.example.deviceapp.entity.Device;
import com.example.deviceapp.entity.DeviceLog;
import com.example.deviceapp.exception.NotFoundException;
import com.example.deviceapp.repository.DeviceLogRepository;
import com.example.deviceapp.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceLogService {

    private final DeviceRepository deviceRepository;
    private final DeviceLogRepository deviceLogRepository;

    public DeviceLogResponse addLog(Long deviceId, DeviceLogRequest request) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new NotFoundException("Device not found: " + deviceId));

        DeviceLog log = DeviceLog.builder()
                .device(device)
                .logLevel(request.logLevel())
                .message(request.message())
                .createdAt(LocalDateTime.now())
                .build();

        DeviceLog saved = deviceLogRepository.save(log);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<DeviceLogResponse> getLogs(Long deviceId,
                                           LocalDateTime from,
                                           LocalDateTime to) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new NotFoundException("Device not found: " + deviceId));

        List<DeviceLog> logs;
        if (from != null && to != null) {
            logs = deviceLogRepository.findByDeviceAndCreatedAtBetween(device, from, to);
        } else {
            logs = deviceLogRepository.findByDevice(device);
        }

        return logs.stream()
                .map(this::toResponse)
                .toList();
    }

    private DeviceLogResponse toResponse(DeviceLog log) {
        return new DeviceLogResponse(
                log.getId(),
                log.getDevice().getId(),
                log.getLogLevel(),
                log.getMessage(),
                log.getCreatedAt()
        );
    }
}