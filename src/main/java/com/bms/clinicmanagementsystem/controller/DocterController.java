@GetMapping("/{doctorId}/availability")
public ResponseEntity<?> getDoctorAvailability(
    @RequestParam String userRole,
    @PathVariable Long doctorId,
    @RequestParam String date,       // e.g. "2025-06-17"
    @RequestHeader("Authorization") String token) {

    // Call a service method that handles:
    // - validating the token
    // - checking user role permissions
    // - fetching doctor availability on the given date
    var availability = doctorService.getAvailability(userRole, doctorId, date, token);

    return ResponseEntity.ok(availability);
}
