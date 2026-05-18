package com.healthcare.appointment.service;

import com.healthcare.appointment.entity.Complaint;
import com.healthcare.appointment.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint submitComplaint(Complaint complaint) { return complaintRepository.save(complaint); }
    public List<Complaint> getAllComplaints() { return complaintRepository.findAll(); }
    
    public void updateComplaintStatus(Long id, String status) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(id);
        if (complaintOpt.isPresent()) {
            Complaint complaint = complaintOpt.get();
            complaint.setStatus(status);
            complaintRepository.save(complaint);
        }
    }
}