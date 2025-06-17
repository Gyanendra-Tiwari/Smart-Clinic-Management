package com.bms.clinicmanagementsystem.repository;

import com.bms.clinicmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    // Check if a patient exists by first and last name (case insensitive)
    boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    // Retrieve a patient by email
    Optional<Patient> findByEmail(String email);

    // Retrieve a patient by phone number
    Optional<Patient> findByPhone(String phone);
}
