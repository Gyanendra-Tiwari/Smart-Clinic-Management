@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Method explicitly named for booking appointments
    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Retrieve appointments for a doctor on a given date
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return appointmentRepository.findByDoctorIdAndStartTimeBetween(doctorId, startOfDay, endOfDay);
    }
}
