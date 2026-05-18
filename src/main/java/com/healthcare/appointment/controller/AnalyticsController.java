package com.healthcare.appointment.controller;

import com.healthcare.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/analytics")
public class AnalyticsController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalAppointments", appointmentService.countAll());
        summary.put("cancellations", appointmentService.countByStatus("CANCELLED"));
        // Add more analytics as needed
        return summary;
    }
}