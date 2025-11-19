package com.example.deviceapp.controller;

import com.example.deviceapp.dto.device.DeviceRequest;
import com.example.deviceapp.dto.device.DeviceResponse;
import com.example.deviceapp.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public List<DeviceResponse> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public DeviceResponse getDevice(@PathVariable Long id) {
        return deviceService.getDevice(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceResponse createDevice(@RequestBody @Valid DeviceRequest request) {
        return deviceService.createDevice(request);
    }

    @PutMapping("/{id}")
    public DeviceResponse updateDevice(@PathVariable Long id,
                                       @RequestBody @Valid DeviceRequest request) {
        return deviceService.updateDevice(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }
}