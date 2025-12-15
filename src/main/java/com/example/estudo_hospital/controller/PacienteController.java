package com.example.estudo_hospital.controller;

import com.example.estudo_hospital.dto.PacienteDTO;
import com.example.estudo_hospital.entity.Paciente;
import com.example.estudo_hospital.service.PacienteService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public PacienteDTO buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PostMapping
    public PacienteDTO salvar(@RequestBody PacienteDTO paciente) {
        return pacienteService.cadastrar(paciente);
    }
}
