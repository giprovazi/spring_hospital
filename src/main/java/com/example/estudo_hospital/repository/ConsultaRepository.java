package com.example.estudo_hospital.repository;

import com.example.estudo_hospital.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataConsulta(Long medicoId, LocalDateTime dataHora);
    boolean existsByPacienteIdAndDataConsulta(Long pacienteId, LocalDateTime dataHora);

    List<Consulta> findByPacienteId(Long idPaciente);

    List<Consulta> findByMedicoId(Long medicoId);
}
