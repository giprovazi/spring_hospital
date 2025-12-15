package com.example.estudo_hospital.repository;

import com.example.estudo_hospital.entity.Consulta;
import com.example.estudo_hospital.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByAtivoTrue();
}
