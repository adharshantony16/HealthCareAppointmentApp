package com.healthcare.appointment.service;

import com.healthcare.appointment.dto.MonthlyReportDto;

import com.healthcare.appointment.repository.AppointmentRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import jakarta.mail.MessagingException;

import jakarta.mail.util.ByteArrayDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportingService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	private final JavaMailSender mailSender;

    public ReportingService(JavaMailSender mailSender) {
        
        this.mailSender = mailSender;
    }

	public List<MonthlyReportDto> generateMonthlyReport() {
		LocalDate date = LocalDate.now(); // or LocalDate.parse("2025-08-09")

		int year = date.getYear();
		int month = date.getMonthValue();
		YearMonth ym = YearMonth.of(year, month);
        LocalDateTime start = ym.atDay(1).atStartOfDay();
        LocalDateTime end = ym.atEndOfMonth().atTime(23, 59, 59);

        return appointmentRepository.findMonthlyReport(start, end);
	}

	public Map<String, Object> generateAppointmentReport() {
		Map<String, Object> report = new HashMap<>();

		long totalAppointments = appointmentRepository.count();
		long scheduledAppointments = appointmentRepository.countByStatus("SCHEDULED");
		long completedAppointments = appointmentRepository.countByStatus("COMPLETED");
		long cancelledAppointments = appointmentRepository.countByStatus("CANCELLED");

		report.put("totalAppointments", totalAppointments);
		report.put("scheduledAppointments", scheduledAppointments);
		report.put("completedAppointments", completedAppointments);
		report.put("cancelledAppointments", cancelledAppointments);

		return report;
	}

	public void sendReportByEmail(String email, int year, int month) throws IOException, MessagingException {
        byte[] pdfBytes = generatePdfReport(year, month);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Monthly Report - " + year + "/" + month);
        helper.setText("Please find the attached report PDF.");

        ByteArrayDataSource source = new ByteArrayDataSource(pdfBytes, "application/pdf");
        helper.addAttachment("Monthly_Report_" + year + "_" + month + ".pdf", source);

        mailSender.send(message);
    }
	
	public byte[] generatePdfReport(int year, int month) throws IOException {
        List<MonthlyReportDto> report = generateMonthlyReport();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Monthly Report for " + year + "-" + month).setBold());

        Table table = new Table(3);
        table.addHeaderCell("Doctor ID");
        table.addHeaderCell("Total Appointments");
        table.addHeaderCell("Cancelled Appointments");

        for (MonthlyReportDto dto : report) {
            table.addCell(dto.getDoctorId().toString());
            table.addCell(String.valueOf(dto.getTotalAppointments()));
            table.addCell(String.valueOf(dto.getCancelledAppointments()));
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }


	
}