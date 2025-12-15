package com.example.estudo_hospital.dto;

import java.time.LocalDateTime;

public record ReqBodyConsulta(Long medicoId, Long pacienteId, LocalDateTime dataHora) {
}
