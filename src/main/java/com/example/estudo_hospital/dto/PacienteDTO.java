package com.example.estudo_hospital.dto;

import com.example.estudo_hospital.entity.Consulta;

import java.time.LocalDate;
import java.util.List;

public record PacienteDTO(Long id, String nome, String cpf, LocalDate dataNascimento, List<Consulta> consultas) {
}
