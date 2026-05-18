package com.healthcare.appointment.controller;

import com.healthcare.appointment.entity.User;
import com.healthcare.appointment.entity.Doctor;
import com.healthcare.appointment.service.UserService;
import com.healthcare.appointment.service.DoctorService;
import com.healthcare.appointment.service.ComplaintService;
import com.healthcare.appointment.entity.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users/{id}/block")
    public ResponseEntity<?> blockUser(@PathVariable Long id) {
        userService.blockUser(id);
        return ResponseEntity.ok("User blocked successfully");
    }

    @PutMapping("/users/{id}/unblock")
    public ResponseEntity<?> unblockUser(@PathVariable Long id) {
        userService.unblockUser(id);
        return ResponseEntity.ok("User unblocked successfully");
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/complaints/{id}/status")
    public ResponseEntity<?> updateComplaintStatus(@PathVariable Long id, @RequestParam String status) {
        complaintService.updateComplaintStatus(id, status);
        return ResponseEntity.ok("Complaint status updated");
    }

    @PostMapping("/announcements")
    public ResponseEntity<?> sendAnnouncement(@RequestBody Map<String, String> announcement) {
        // In real app, implement notification service
        return ResponseEntity.ok("Announcement sent successfully");
    }
}
