package com.bms.clinicmanagementsystem.service;

import com.bms.clinicmanagementsystem.model.Doctor;
import com.bms.clinicmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

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
        return doctorRepository.save(existingDoctor);
    }

    // Delete doctor by ID
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
