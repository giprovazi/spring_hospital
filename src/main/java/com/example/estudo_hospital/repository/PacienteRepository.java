package com.example.estudo_hospital.repository;

import com.example.estudo_hospital.entity.Consulta;
import com.example.estudo_hospital.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByCpf(String cpf);
}
