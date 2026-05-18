package com.healthcare.appointment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.healthcare.appointment.entity.Appointment;
import com.healthcare.appointment.entity.Doctor;
import com.healthcare.appointment.entity.Patient;
import com.healthcare.appointment.repository.AppointmentRepository;


@Service
public class NotificationService {
	
	private final EmailSenderService emailSenderService;
	
	private final AppointmentRepository appointmentRepository;
	private final PatientService patientService;
	private final DoctorService doctorService;
	

	public NotificationService(AppointmentRepository appointmentRepository, PatientService patientService,
			DoctorService doctorService, EmailSenderService emailSenderService) {
		this.appointmentRepository = appointmentRepository;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.emailSenderService = emailSenderService;
	}

	
    public void sendAppointmentConfirmation(String to, String message) {
    	String subject = "Appointment confirmation";
    	emailSenderService.sendEmail(to, subject, message);
    }

    @Scheduled(cron = "0 */5 * * * * ")
	public void notifyUpcomingAppointments() {
    	System.out.println("Hello");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nextHour = now.plusMinutes(60);

		List<Appointment> appointments = appointmentRepository.findByAppointmentTimeBetweenAndStatus(now, nextHour,
				"BOOKED");

		for (Appointment appt : appointments) {
			try {
				// Get patient info
				Optional<Patient> patOpt = patientService.getPatientById(appt.getPatient().getId());
				Patient patient = null;

				if (patOpt.isPresent()) {
				    patient = patOpt.get();
				}


				if (patient.getEmail() != null && !patient.getEmail().isBlank()) {
					String subject = "Reminder: Upcoming Appointment";
					String message = String.format(
							"Hello %s,\n\nThis is a reminder of your appointment at %s.\n\nRegards,\nClinic Team",
							patient.getName(), appt.getAppointmentTime());

					emailSenderService.sendEmail(patient.getEmail(), subject, message);
				}

				// Get doctor info
				Doctor doctor = doctorService
				        .getDoctorById(appt.getDoctor().getId())
				        .orElse(null);


				if (doctor.getEmail() != null && !doctor.getEmail().isBlank()) {
					String subject = "Upcoming Appointment Scheduled";
					String message = String.format(
							"Hello Dr. %s,\n\nYou have an appointment scheduled at %s.\n\nRegards,\nClinic Team",doctor.getName(), appt.getAppointmentTime());

					emailSenderService.sendEmail(doctor.getEmail(), subject, message);
				}

			} catch (Exception e) {
				// Log and continue with the next appointment
				System.err
						.println("Failed to send reminder for appointment ID " + appt.getId() + ": " + e.getMessage());
			}
		}
	}
}