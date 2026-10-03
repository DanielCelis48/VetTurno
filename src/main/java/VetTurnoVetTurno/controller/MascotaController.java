package VetTurnoVetTurno.controller;

import VetTurnoVetTurno.dto.MascotaDTO;
import VetTurnoVetTurno.dto.MascotaRequest;
import VetTurnoVetTurno.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> crear(@RequestBody MascotaRequest request) {
        return new ResponseEntity<>(mascotaService.crear(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> listar() {
        return ResponseEntity.ok(mascotaService.listarTodas());
    }
}