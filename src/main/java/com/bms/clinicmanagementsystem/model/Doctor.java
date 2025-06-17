package com.bms.clinicmanagementsystem.controller;

import com.bms.clinicmanagementsystem.model.TimeSlot; // Assuming you have this model
import com.bms.clinicmanagementsystem.service.DoctorService;
import com.bms.clinicmanagementsystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DocterController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    /**
     * Endpoint to get doctor availability based on user role, doctor ID, date, and token.
     * 
     * @param role User role making the request (e.g., admin, patient)
     * @param doctorId ID of the doctor
     * @param date Date for which availability is requested (yyyy-MM-dd)
     * @param token JWT token for authorization
     * @return List of available time slots or unauthorized/error status
     */
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestParam String role,
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestHeader("Authorization") String token) {

        // Validate token
        if (!tokenService.validateToken(token)) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }

        // Optionally verify user role - example: only patients and admins allowed
        if (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("patient")) {
            return new ResponseEntity<>("Access denied for role: " + role, HttpStatus.FORBIDDEN);
        }

        // Fetch availability from service
        List<TimeSlot> availableSlots = doctorService.getDoctorAvailability(doctorId, date);

        if (availableSlots == null || availableSlots.isEmpty()) {
            return new ResponseEntity<>("No availability found for doctor on " + date, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(availableSlots);
    }
}
