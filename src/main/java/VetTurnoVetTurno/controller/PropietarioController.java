package VetTurnoVetTurno.controller;

import VetTurnoVetTurno.dto.PropietarioDTO;
import VetTurnoVetTurno.dto.PropietarioRequest;
import VetTurnoVetTurno.service.PropietarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
public class PropietarioController {

    private final PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    @PostMapping
    public ResponseEntity<PropietarioDTO> crear(@RequestBody PropietarioRequest request) {
        return new ResponseEntity<>(propietarioService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropietarioDTO>> listar() {
        return ResponseEntity.ok(propietarioService.listarTodos());
    }
}