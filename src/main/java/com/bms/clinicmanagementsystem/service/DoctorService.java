package com.bms.clinicmanagementsystem.service;

import com.bms.clinicmanagementsystem.model.Appointment;
import com.bms.clinicmanagementsystem.model.Doctor;
import com.bms.clinicmanagementsystem.repository.AppointmentRepository;
import com.bms.clinicmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Create a new doctor
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    // Update doctor by ID
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setFirstName(updatedDoctor.getFirstName());
        existingDoctor.setLastName(updatedDoctor.getLastName());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setPhone(updatedDoctor.getPhone());
        // Add password update if needed
        return doctorRepository.save(existingDoctor);
    }

    // Delete doctor by ID
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }

    /**
     * Validate doctor login credentials.
     * For simplicity, assuming password is stored in plain text (not recommended in production).
     *
     * @param email    doctor's email
     * @param password doctor's password
     * @return doctor if credentials are valid, else throw exception
     */
    public Doctor validateDoctorLogin(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!doctor.getPassword().equals(password)) { // Ideally use hashed passwords
            throw new RuntimeException("Invalid email or password");
        }
        return doctor;
    }

    /**
     * Get list of appointments (availability) for a doctor on a specific date.
     *
     * @param doctorId ID of the doctor
     * @param date     Date to check availability
     * @return List of appointments for the doctor on that date
     */
    public List<Appointment> getDoctorAppointmentsByDate(Long doctorId, LocalDate date) {
        // Start and end of the day
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return appointmentRepository.findByDoctorIdAndStartTimeBetween(doctorId, startOfDay, endOfDay);
    }
}
