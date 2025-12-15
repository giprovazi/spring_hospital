package com.example.estudo_hospital.service;

import com.example.estudo_hospital.dto.MedicoDTO;
import com.example.estudo_hospital.entity.Medico;
import com.example.estudo_hospital.exception.ResourceNotFoundException;
import com.example.estudo_hospital.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoDTO cadastrar(MedicoDTO medicoDTO){
        Medico medico = new Medico();
        medico.setNome(medicoDTO.nome());
        medico.setEspecialidade(medicoDTO.especialidade());
        medico.setAtivo(true);
        medicoRepository.save(medico);

        return new MedicoDTO(
                medico.getId(),
                medico.getNome(),
                medico.getEspecialidade(),
                medico.getAtivo(),
                medico.getConsultas()
        );
    }

    public MedicoDTO buscarPorId(long id){
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado"));
        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getEspecialidade(),  medico.getAtivo(), medico.getConsultas());
    }

    @Transactional(readOnly = true)
    public List<MedicoDTO> listarAtivos(){
        List<Medico> medicos = medicoRepository.findByAtivoTrue();
        return medicos.stream().map(medico -> new MedicoDTO(medico.getId(), medico.getNome(), medico.getEspecialidade(),  medico.getAtivo(), medico.getConsultas()))
                .toList();
    }

    public Medico buscarEntidadePorId(Long medicoId) {
        return medicoRepository.findById(medicoId).orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado"));
    }
}
