package com.healthcare.appointment.controller;

import com.healthcare.appointment.entity.Doctor;
import com.healthcare.appointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialization/{spec}")
    public List<Doctor> getBySpecialization(@PathVariable String spec) {
        return doctorService.findBySpecialization(spec);
    }

    @GetMapping("/location/{loc}")
    public List<Doctor> getByLocation(@PathVariable String loc) {
        return doctorService.findByLocation(loc);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Doctor> addDoctor(@PathVariable Long userId, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(userId, doctor));
    }
    
    }
