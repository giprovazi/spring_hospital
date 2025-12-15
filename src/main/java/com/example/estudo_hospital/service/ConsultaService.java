package com.example.estudo_hospital.service;

import com.example.estudo_hospital.dto.ConsultaDTO;
import com.example.estudo_hospital.dto.MedicoDTO;
import com.example.estudo_hospital.dto.PacienteDTO;
import com.example.estudo_hospital.entity.Consulta;
import com.example.estudo_hospital.entity.Medico;
import com.example.estudo_hospital.entity.Paciente;
import com.example.estudo_hospital.entity.Status;
import com.example.estudo_hospital.exception.BusinessException;
import com.example.estudo_hospital.exception.ResourceNotFoundException;
import com.example.estudo_hospital.repository.ConsultaRepository;
import com.example.estudo_hospital.repository.MedicoRepository;
import com.example.estudo_hospital.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private MedicoService medicoService;
    private PacienteService pacienteService;

    public ConsultaService(ConsultaRepository consultaRepository, MedicoService medicoService,
                           PacienteService pacienteService) {
        this.consultaRepository = consultaRepository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    @Transactional
    public ConsultaDTO marcarConsulta(Long medicoId, Long pacienteId, LocalDateTime dataHora){
        if (medicoId == null || pacienteId == null || dataHora == null) {
            throw new IllegalArgumentException("Médico, paciente e data/hora são obrigatórios.");
        }

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new BusinessException("Não é possível agendar em data/hora passada.");
        }

        Medico medico = medicoService.buscarEntidadePorId(medicoId);
        Paciente paciente = pacienteService.buscarEntidadePorId(pacienteId);

        if (!medico.getAtivo()){
            throw new BusinessException("O médico não está ativo");
        }

        if (consultaRepository.existsByMedicoIdAndDataConsulta(medicoId, dataHora)) {
            throw new BusinessException("Médico já possui consulta neste horário.");
        }

        if (consultaRepository.existsByPacienteIdAndDataConsulta(pacienteId, dataHora)) {
            throw new BusinessException("Paciente já possui consulta neste horário.");
        }

        int hora = dataHora.getHour();
        if (hora < 8 || hora > 18) {
            throw new BusinessException("Fora do horário de atendimento (08:00 - 18:00).");
        }

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataConsulta(dataHora);
        consulta.setStatus(Status.AGENDADA);

        consultaRepository.save(consulta);

        return new ConsultaDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getMedico().getNome(), consulta.getPaciente().getId(), consulta.getPaciente().getNome(), consulta.getStatus(), consulta.getDataConsulta());
    }

    public ConsultaDTO buscarPorId (Long id){
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        return new ConsultaDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getMedico().getNome(), consulta.getPaciente().getId(), consulta.getPaciente().getNome(), consulta.getStatus(), consulta.getDataConsulta());
    }

    public List<ConsultaDTO> listarConsultas(){
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream().map(consulta -> new ConsultaDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getMedico().getNome(), consulta.getPaciente().getId(), consulta.getPaciente().getNome(), consulta.getStatus(), consulta.getDataConsulta()))
                .toList();
    }

    public List<ConsultaDTO> listarConsultaPorPaciente(Long idPaciente){
        List<Consulta> consultas = consultaRepository.findByPacienteId(idPaciente);
        return consultas.stream().map(consulta -> new ConsultaDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getMedico().getNome(), consulta.getPaciente().getId(), consulta.getPaciente().getNome(), consulta.getStatus(), consulta.getDataConsulta()))
                .toList();
    }

    public List<ConsultaDTO> listarConsultaPorMedico(Long medicoId){
        List<Consulta> consultas = consultaRepository.findByMedicoId(medicoId);
        return consultas.stream().map(consulta -> new ConsultaDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getMedico().getNome(), consulta.getPaciente().getId(), consulta.getPaciente().getNome(), consulta.getStatus(), consulta.getDataConsulta()))
                .toList();
    }

    public void finalizarConsulta(Long consultaId){
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        consulta.setStatus(Status.REALIZADA);
        consultaRepository.save(consulta);
    }
}
