package com.example.estudo_hospital.dto;

import com.example.estudo_hospital.entity.Status;

import java.time.LocalDateTime;

public record ConsultaDTO(Long id, Long idMedico, String nomeMedico, Long idPaciente, String nomePaciente, Status status, LocalDateTime dataConsulta) {
}
