package br.com.viniciusoliveira.hospital.scheduling.patient.dto.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record PatientResponseDto(
        UUID id,
        String fullName,
        String email,
        LocalDate birthDate,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
