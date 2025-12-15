package com.example.estudo_hospital.controller;

import com.example.estudo_hospital.dto.ConsultaDTO;
import com.example.estudo_hospital.dto.ReqBodyConsulta;
import com.example.estudo_hospital.service.ConsultaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ConsultaDTO marcaConsulta (@RequestBody ReqBodyConsulta reqBodyConsulta) {
        return consultaService.marcarConsulta(reqBodyConsulta.medicoId(), reqBodyConsulta.pacienteId(), reqBodyConsulta.dataHora());
    }

    @GetMapping("/{id}")
    public ConsultaDTO buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<ConsultaDTO> listarTodasConsultas() {
        return consultaService.listarConsultas();
    }

    @GetMapping("listar/medico/{medicoId}")
    public List<ConsultaDTO> listarMedicoConsultas(@PathVariable Long medicoId) {
        return consultaService.listarConsultaPorMedico(medicoId);
    }

    @GetMapping("/listar/paciente/{pacienteId}")
    public List<ConsultaDTO> listarPacienteConsultas(@PathVariable Long pacienteId) {
        return consultaService.listarConsultaPorPaciente(pacienteId);
    }

    @PutMapping("/finalizar/{consultaId}")
    public String finalizarConsulta(@PathVariable Long consultaId) {
        consultaService.finalizarConsulta(consultaId);
        return "Consulta finalizada";
    }
}
