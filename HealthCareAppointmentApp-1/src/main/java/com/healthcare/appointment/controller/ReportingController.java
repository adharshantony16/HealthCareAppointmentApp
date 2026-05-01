package com.healthcare.appointment.controller;

import com.healthcare.appointment.dto.MonthlyReportDto;
import com.healthcare.appointment.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    @Autowired
    private ReportingService reportingService;

    @GetMapping("/monthly")
    public ResponseEntity<List<MonthlyReportDto>> generateMonthlyReport() {
        List<MonthlyReportDto> report = reportingService.generateMonthlyReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/appointments")
    public ResponseEntity<Map<String, Object>> getAppointmentReport() {
        Map<String, Object> report = reportingService.generateAppointmentReport();
        return ResponseEntity.ok(report);
    }

    @PostMapping("/monthly/pdf")
	public ResponseEntity<String> exportAndEmailReport(@RequestParam String email) {
		try {
			LocalDate date = LocalDate.now(); // or LocalDate.parse("2025-08-09")
	        
	        int year = date.getYear();
	        int month = date.getMonthValue();
			reportingService.sendReportByEmail(email, year, month);
			return ResponseEntity.ok("Report emailed successfully to " + email);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send report: " + e.getMessage());
		}
	}


}
