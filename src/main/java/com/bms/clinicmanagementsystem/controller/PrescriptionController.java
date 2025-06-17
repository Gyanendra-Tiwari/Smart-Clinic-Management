package com.bms.clinicmanagementsystem.controller;

import com.bms.clinicmanagementsystem.dto.PrescriptionDto;
import com.bms.clinicmanagementsystem.model.Prescription;
import com.bms.clinicmanagementsystem.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * Create a new prescription.
     * Requires a token header for authorization.
     */
    @PostMapping
    public ResponseEntity<Prescription> createPrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody PrescriptionDto prescriptionDto) {
        // You should validate the token or pass it down to the service for auth checks
        Prescription created = prescriptionService.createPrescription(prescriptionDto, token);
        return ResponseEntity.ok(created);
    }

    /**
     * Get all prescriptions.
     * Token header is required for authorization.
     */
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions(
            @RequestHeader("Authorization") String token) {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions(token);
        return ResponseEntity.ok(prescriptions);
    }

    /**
     * Get a prescription by ID.
     * Token header is required for authorization.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id, token);
        return ResponseEntity.ok(prescription);
    }

    /**
     * Update an existing prescription.
     * Token header is required for authorization.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @Valid @RequestBody PrescriptionDto updatedDetails) {
        Prescription updated = prescriptionService.updatePrescription(id, updatedDetails, token);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a prescription by ID.
     * Token header is required for authorization.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {
        prescriptionService.deletePrescription(id, token);
        return ResponseEntity.noContent().build();
    }
}
