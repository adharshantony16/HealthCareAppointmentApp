package com.healthcare.appointment.controller;

import com.healthcare.appointment.entity.WaitlistEntry;
import com.healthcare.appointment.service.WaitlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/waitlist")
public class WaitlistController {
    @Autowired
    private WaitlistService waitlistService;

    @PostMapping
    public WaitlistEntry addToWaitlist(@RequestBody WaitlistEntry entry) {
        return waitlistService.addToWaitlist(entry);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<WaitlistEntry> getWaitlistForDoctor(@PathVariable Long doctorId) {
        return waitlistService.getWaitlistForDoctor(doctorId);
    }
}