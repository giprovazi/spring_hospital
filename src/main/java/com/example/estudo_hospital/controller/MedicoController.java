package com.example.estudo_hospital.controller;

import com.example.estudo_hospital.dto.MedicoDTO;
import com.example.estudo_hospital.dto.PacienteDTO;
import com.example.estudo_hospital.entity.Medico;
import com.example.estudo_hospital.service.MedicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public MedicoDTO salvar(@RequestBody MedicoDTO medico) {
        return medicoService.cadastrar(medico);
    }

    @GetMapping("/{id}")
    public MedicoDTO buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id);
    }

    @GetMapping
    public List<MedicoDTO> buscarAtivos() {
        return medicoService.listarAtivos();
    }
}
