package com.healthcare.appointment.service;
 
import com.healthcare.appointment.entity.Doctor;
import com.healthcare.appointment.entity.User;
import com.healthcare.appointment.repository.DoctorRepository;
import com.healthcare.appointment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
 
@Service
public class DoctorService {
 
    @Autowired
    private DoctorRepository doctorRepository;
 
    @Autowired
    private UserRepository userRepository;
 
 
    public Doctor saveDoctor(Long userId, Doctor doctorDetails) {
       
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
 
        
        Doctor doctor = new Doctor();
        doctor.setUser(existingUser); 
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setLocation(doctorDetails.getLocation());
        doctor.setPhone(doctorDetails.getPhone());
        doctor.setRating(doctorDetails.getRating());
        doctor.setName(doctorDetails.getName());          
        doctor.setEmail(doctorDetails.getEmail());

 
        return doctorRepository.save(doctor);
    }
 
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
 
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }
 
    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
 
    public List<Doctor> findByLocation(String location) {
        return doctorRepository.findByLocation(location);
    }
}
 
 