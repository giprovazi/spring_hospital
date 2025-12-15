package com.example.estudo_hospital.service;

import com.example.estudo_hospital.dto.PacienteDTO;
import com.example.estudo_hospital.entity.Paciente;
import com.example.estudo_hospital.exception.BusinessException;
import com.example.estudo_hospital.exception.ResourceNotFoundException;
import com.example.estudo_hospital.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteDTO cadastrar(PacienteDTO pacienteDTO) {
        if (pacienteRepository.existsByCpf(pacienteDTO.cpf())) {
            throw new BusinessException("CPF já cadastrado");
        }

        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.nome());
        paciente.setCpf(pacienteDTO.cpf());
        paciente.setDataNascimento(pacienteDTO.dataNascimento());

        pacienteRepository.save(paciente);

        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getConsultas()
        );
    }

    public PacienteDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getConsultas()
        );
    }

    public Paciente buscarEntidadePorId(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
    }
}
