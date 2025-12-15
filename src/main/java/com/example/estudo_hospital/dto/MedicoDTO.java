package com.example.estudo_hospital.dto;

import com.example.estudo_hospital.entity.Consulta;

import java.util.List;

public record MedicoDTO(Long id, String nome, String especialidade, Boolean ativo, List<Consulta> consultas) {
}
