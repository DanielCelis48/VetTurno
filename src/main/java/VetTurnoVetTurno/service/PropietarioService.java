package VetTurnoVetTurno.service;

import VetTurnoVetTurno.dto.PropietarioDTO;
import VetTurnoVetTurno.dto.PropietarioRequest;
import VetTurnoVetTurno.model.Propietario;
import VetTurnoVetTurno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropietarioService {

    private final PropietarioRepository propietarioRepository;

    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    public PropietarioDTO crear(PropietarioRequest request) {
        Propietario propietario = new Propietario(request.getNombre(), request.getTelefono(), request.getEmail());
        Propietario guardado = propietarioRepository.save(propietario);
        return aDTO(guardado);
    }

    public List<PropietarioDTO> listarTodos() {
        return propietarioRepository.findAll().stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    private PropietarioDTO aDTO(Propietario p) {
        return new PropietarioDTO(p.getId(), p.getNombre(), p.getTelefono(), p.getEmail());
    }
}