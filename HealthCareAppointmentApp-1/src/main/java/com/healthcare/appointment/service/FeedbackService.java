package com.healthcare.appointment.service;

import com.healthcare.appointment.entity.Feedback;
import com.healthcare.appointment.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback submitFeedback(Feedback feedback) { return feedbackRepository.save(feedback); }
    public List<Feedback> getFeedbackForDoctor(Long doctorId) { return feedbackRepository.findAll(); }
}