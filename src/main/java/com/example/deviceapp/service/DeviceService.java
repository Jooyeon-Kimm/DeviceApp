package com.example.deviceapp.service;

import com.example.deviceapp.dto.device.DeviceRequest;
import com.example.deviceapp.dto.device.DeviceResponse;
import com.example.deviceapp.entity.Device;
import com.example.deviceapp.entity.DeviceStatus;
import com.example.deviceapp.exception.NotFoundException;
import com.example.deviceapp.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Transactional(readOnly = true)
    public List<DeviceResponse> getAllDevices() {
        return deviceRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DeviceResponse getDevice(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Device not found: " + id));
        return toResponse(device);
    }

    public DeviceResponse createDevice(DeviceRequest request) {
        Device device = Device.builder()
                .name(request.name())
                .serialNumber(request.serialNumber())
                .status(DeviceStatus.ONLINE)
                .lastUpdatedAt(LocalDateTime.now())
                .build();
        Device saved = deviceRepository.save(device);
        return toResponse(saved);
    }

    public DeviceResponse updateDevice(Long id, DeviceRequest request) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Device not found: " + id));

        device.setName(request.name());
        device.setSerialNumber(request.serialNumber());
        device.setLastUpdatedAt(LocalDateTime.now());

        return toResponse(device);
    }

    public void deleteDevice(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new NotFoundException("Device not found: " + id);
        }
        deviceRepository.deleteById(id);
    }

    private DeviceResponse toResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getName(),
                device.getSerialNumber(),
                device.getStatus(),
                device.getLastUpdatedAt()
        );
    }
}