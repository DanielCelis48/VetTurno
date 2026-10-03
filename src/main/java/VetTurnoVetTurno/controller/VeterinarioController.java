package VetTurnoVetTurno.controller;

import VetTurnoVetTurno.dto.VeterinarioDTO;
import VetTurnoVetTurno.dto.VeterinarioRequest;
import VetTurnoVetTurno.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @PostMapping
    public ResponseEntity<VeterinarioDTO> crear(@RequestBody VeterinarioRequest request) {
        return new ResponseEntity<>(veterinarioService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioDTO>> listar() {
        return ResponseEntity.ok(veterinarioService.listarTodos());
    }
}