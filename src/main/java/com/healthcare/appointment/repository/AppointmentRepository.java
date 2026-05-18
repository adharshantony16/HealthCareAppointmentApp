package com.healthcare.appointment.repository;

import com.healthcare.appointment.dto.MonthlyReportDto;
import com.healthcare.appointment.entity.Appointment;
import com.healthcare.appointment.entity.Doctor;
import com.healthcare.appointment.entity.Patient;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDoctorAndAppointmentTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
    long countByStatus(String status);
    List<Appointment> findByAppointmentTimeBetweenAndStatus(LocalDateTime start, LocalDateTime end, String status);

    @Query("""
    	    SELECT new com.healthcare.appointment.dto.MonthlyReportDto(
    	        a.doctor.id,
    	        COUNT(a),
    	        SUM(CASE WHEN a.status = 'CANCELLED' THEN 1L ELSE 0L END)
    	    )
    	    FROM Appointment a
    	    WHERE a.appointmentTime BETWEEN :start AND :end
    	    GROUP BY a.doctor.id
    	    """)
 	List<MonthlyReportDto> findMonthlyReport(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}