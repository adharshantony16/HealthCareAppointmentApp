package com.healthcare.appointment.controller;

import com.healthcare.appointment.dto.AppointmentRequestDto;
import com.healthcare.appointment.entity.Appointment;
import com.healthcare.appointment.entity.Doctor;
import com.healthcare.appointment.entity.Patient;
import com.healthcare.appointment.service.AppointmentService;
import com.healthcare.appointment.service.DoctorService;
import com.healthcare.appointment.service.PatientService;
import com.healthcare.appointment.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@Valid @RequestBody AppointmentRequestDto dto, Principal principal) {
        Optional<Doctor> doctorOpt = doctorService.getDoctorById(dto.getDoctorId());
        Optional<Patient> patientOpt = patientService.getPatientById(dto.getPatientId());

        if (doctorOpt.isPresent() && patientOpt.isPresent()) {
            Appointment appointment = new Appointment();
            appointment.setDoctor(doctorOpt.get());
            appointment.setPatient(patientOpt.get());
            appointment.setAppointmentTime(dto.getAppointmentTime());
            appointment.setStatus("BOOKED");
            appointment.setNotes(dto.getNotes());
            Appointment saved = appointmentService.bookAppointment(appointment);

            notificationService.sendAppointmentConfirmation(
                patientOpt.get().getEmail(),
                "Your appointment is booked with Dr. " + doctorOpt.get().getName()
            );

            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.badRequest().body("Invalid doctor or patient");
    }

    @PutMapping("/{id}/reschedule")
    public ResponseEntity<?> rescheduleAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDto dto) {
        Optional<Appointment> appointmentOpt = appointmentService.getAppointmentById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setAppointmentTime(dto.getAppointmentTime());
            appointment.setNotes(dto.getNotes());
            Appointment updated = appointmentService.bookAppointment(appointment);
            
            notificationService.sendAppointmentConfirmation(
                appointment.getPatient().getEmail(),
                "Your appointment has been rescheduled with Dr. " + appointment.getDoctor().getName()
            );
            
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        Optional<Appointment> appointmentOpt = appointmentService.getAppointmentById(id);
        if (appointmentOpt.isPresent()) {
            appointmentService.cancelAppointment(id);
            notificationService.sendAppointmentConfirmation(
                appointmentOpt.get().getPatient().getEmail(),
                "Your appointment has been cancelled"
            );
            return ResponseEntity.ok("Appointment cancelled");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        Optional<Doctor> doctorOpt = doctorService.getDoctorById(doctorId);
        if (doctorOpt.isPresent()) {
            List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorOpt.get());
            return ResponseEntity.ok(appointments);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
        Optional<Patient> patientOpt = patientService.getPatientById(patientId);
        if (patientOpt.isPresent()) {
            List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patientOpt.get());
            return ResponseEntity.ok(appointments);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateAppointmentStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Appointment> appointmentOpt = appointmentService.getAppointmentById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(status);
            Appointment updated = appointmentService.bookAppointment(appointment);
            
            notificationService.sendAppointmentConfirmation(
                    appointment.getPatient().getEmail(),
                    "Your appointment status has changed"
                );
            
            
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
}