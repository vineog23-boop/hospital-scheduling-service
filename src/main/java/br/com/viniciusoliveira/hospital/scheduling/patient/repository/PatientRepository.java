package br.com.viniciusoliveira.hospital.scheduling.patient.repository;

import br.com.viniciusoliveira.hospital.scheduling.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
