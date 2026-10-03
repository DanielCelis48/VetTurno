package VetTurnoVetTurno.service;

import VetTurnoVetTurno.dto.MascotaDTO;
import VetTurnoVetTurno.dto.MascotaRequest;
import VetTurnoVetTurno.model.Mascota;
import VetTurnoVetTurno.model.Propietario;
import VetTurnoVetTurno.repository.MascotaRepository;
import VetTurnoVetTurno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;

    public MascotaService(MascotaRepository mascotaRepository, PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    public MascotaDTO crear(MascotaRequest request) {
        Propietario propietario = propietarioRepository.findById(request.getPropietarioId())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con id: " + request.getPropietarioId()));

        Mascota mascota = new Mascota(request.getNombre(), request.getEspecie(), request.getRaza(), propietario);
        Mascota guardada = mascotaRepository.save(mascota);
        return aDTO(guardada);
    }

    public List<MascotaDTO> listarTodas() {
        return mascotaRepository.findAll().stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    private MascotaDTO aDTO(Mascota m) {
        return new MascotaDTO(
                m.getId(),
                m.getNombre(),
                m.getEspecie(),
                m.getRaza(),
                m.getPropietario().getId(),
                m.getPropietario().getNombre()
        );
    }
}