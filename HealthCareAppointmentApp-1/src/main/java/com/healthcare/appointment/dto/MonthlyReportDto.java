package com.healthcare.appointment.dto;

public class MonthlyReportDto {
    private Long doctorId;
    private Long totalAppointments;
    private Long cancelledAppointments;
    private double cancellationRate; // percentage

    // Constructor
    public MonthlyReportDto(Long doctorId, Long totalAppointments, Long cancelledAppointments) {
        this.doctorId = doctorId;
        this.totalAppointments = totalAppointments;
        this.cancelledAppointments = cancelledAppointments;
        this.cancellationRate = totalAppointments == 0 ? 0 :
            (cancelledAppointments * 100.0) / totalAppointments;
    }

    // Getters & setters

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getTotalAppointments() {
        return totalAppointments;
    }

    public Long getCancelledAppointments() {
        return cancelledAppointments;
    }

    public double getCancellationRate() {
        return cancellationRate;
    }
}