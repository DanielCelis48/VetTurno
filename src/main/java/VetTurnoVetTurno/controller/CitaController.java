package VetTurnoVetTurno.controller;

import VetTurnoVetTurno.dto.CitaDTO;
import VetTurnoVetTurno.dto.CitaRequest;
import VetTurnoVetTurno.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<CitaDTO> agendar(@RequestBody CitaRequest request) {
        return new ResponseEntity<>(citaService.agendarCita(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }

    @GetMapping("/veterinario/{id}")
    public ResponseEntity<List<CitaDTO>> listarPorVeterinario(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.listarPorVeterinario(id));
    }
}