package com.example.deviceapp.repository;

import com.example.deviceapp.entity.Device;
import com.example.deviceapp.entity.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DeviceLogRepository extends JpaRepository<DeviceLog, Long> {

    List<DeviceLog> findByDevice(Device device);

    List<DeviceLog> findByDeviceAndCreatedAtBetween(Device device,
                                                    LocalDateTime from,
                                                    LocalDateTime to);
}