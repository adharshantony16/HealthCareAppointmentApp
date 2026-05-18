package com.healthcare.appointment.controller;

import com.healthcare.appointment.entity.Appointment;
import com.healthcare.appointment.entity.Doctor;
import com.healthcare.appointment.service.AppointmentService;
import com.healthcare.appointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctor-management")
public class DoctorManagementController {
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable Long doctorId) {
        Optional<Doctor> doctorOpt = doctorService.getDoctorById(doctorId);
        if (doctorOpt.isPresent()) {
            List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorOpt.get());
            return ResponseEntity.ok(appointments);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{doctorId}/upcoming")
    public ResponseEntity<List<Appointment>> getUpcomingAppointments(@PathVariable Long doctorId) {
        Optional<Doctor> doctorOpt = doctorService.getDoctorById(doctorId);
        if (doctorOpt.isPresent()) {
            List<Appointment> appointments = appointmentService.getUpcomingAppointments(doctorOpt.get());
            return ResponseEntity.ok(appointments);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{doctorId}/availability")
    public ResponseEntity<?> updateAvailability(@PathVariable Long doctorId, @RequestBody Map<String, Object> availability) {
        // In real app, implement availability schedule
        return ResponseEntity.ok("Availability updated successfully");
    }

    @PostMapping("/{doctorId}/consultation-notes")
    public ResponseEntity<?> addConsultationNotes(@PathVariable Long doctorId, @RequestParam Long appointmentId, @RequestBody String notes) {
        Optional<Appointment> appointmentOpt = appointmentService.getAppointmentById(appointmentId);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setNotes(notes);
            appointmentService.bookAppointment(appointment);
            return ResponseEntity.ok("Consultation notes added");
        }
        return ResponseEntity.notFound().build();
    }

}
