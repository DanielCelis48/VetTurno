package VetTurnoVetTurno.service;

import VetTurnoVetTurno.dto.CitaDTO;
import VetTurnoVetTurno.dto.CitaRequest;
import VetTurnoVetTurno.model.Cita;
import VetTurnoVetTurno.model.Mascota;
import VetTurnoVetTurno.model.Veterinario;
import VetTurnoVetTurno.repository.CitaRepository;
import VetTurnoVetTurno.repository.MascotaRepository;
import VetTurnoVetTurno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    public CitaDTO agendarCita(CitaRequest request) {
        if (request.getFechaHora() == null || request.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha y hora de la cita debe ser en el futuro.");
        }
        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + request.getMascotaId()));

        Veterinario veterinario = veterinarioRepository.findById(request.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con ID: " + request.getVeterinarioId()));

        if (citaRepository.existsByVeterinarioIdAndFechaHora(request.getVeterinarioId(), request.getFechaHora())) {
            throw new IllegalArgumentException("El veterinario ya tiene una cita agendada para la fecha y hora seleccionada.");
        }

        Cita cita = new Cita(request.getFechaHora(), request.getMotivo(), mascota, veterinario);
        Cita guardada = citaRepository.save(cita);

        return aDTO(guardada);
    }

    public List<CitaDTO> listarTodas() {
        return citaRepository.findAll().stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    public List<CitaDTO> listarPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId).stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    private CitaDTO aDTO(Cita c) {
        return new CitaDTO(
                c.getId(),
                c.getFechaHora(),
                c.getMotivo(),
                c.getMascota().getId(),
                c.getMascota().getNombre(),
                c.getMascota().getPropietario().getNombre(),
                c.getVeterinario().getId(),
                c.getVeterinario().getNombre()
        );
    }
}