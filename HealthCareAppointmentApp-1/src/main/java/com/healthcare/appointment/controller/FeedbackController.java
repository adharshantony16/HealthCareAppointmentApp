package com.healthcare.appointment.controller;

import com.healthcare.appointment.entity.Feedback;
import com.healthcare.appointment.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Feedback submitFeedback(@RequestBody Feedback feedback) {
        return feedbackService.submitFeedback(feedback);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Feedback> getFeedbackForDoctor(@PathVariable Long doctorId) {
        return feedbackService.getFeedbackForDoctor(doctorId);
    }
}