package com.healthcare.appointment.service;

import com.healthcare.appointment.entity.WaitlistEntry;
import com.healthcare.appointment.repository.WaitlistEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WaitlistService {
    @Autowired
    private WaitlistEntryRepository waitlistEntryRepository;

    public WaitlistEntry addToWaitlist(WaitlistEntry entry) { return waitlistEntryRepository.save(entry); }
    public List<WaitlistEntry> getWaitlistForDoctor(Long doctorId) { return waitlistEntryRepository.findAll(); }
}