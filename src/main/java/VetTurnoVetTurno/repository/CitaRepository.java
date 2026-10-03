package VetTurnoVetTurno.repository;

import VetTurnoVetTurno.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    // Consulta para verificar si ya existe una cita para el mismo veterinario a la misma fecha/hora
    boolean existsByVeterinarioIdAndFechaHora(Long veterinarioId, LocalDateTime fechaHora);

    // Consulta para filtrar todas las citas asociadas a un veterinario específico
    List<Cita> findByVeterinarioId(Long veterinarioId);
}