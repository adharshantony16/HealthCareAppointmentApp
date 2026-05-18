package com.healthcare.appointment.repository;

import com.healthcare.appointment.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}