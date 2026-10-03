package VetTurnoVetTurno.service;

import VetTurnoVetTurno.dto.VeterinarioDTO;
import VetTurnoVetTurno.dto.VeterinarioRequest;
import VetTurnoVetTurno.model.Veterinario;
import VetTurnoVetTurno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public VeterinarioDTO crear(VeterinarioRequest request) {
        Veterinario veterinario = new Veterinario(request.getNombre(), request.getEspecialidad());
        Veterinario guardado = veterinarioRepository.save(veterinario);
        return aDTO(guardado);
    }

    public List<VeterinarioDTO> listarTodos() {
        return veterinarioRepository.findAll().stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    private VeterinarioDTO aDTO(Veterinario v) {
        return new VeterinarioDTO(v.getId(), v.getNombre(), v.getEspecialidad());
    }
}