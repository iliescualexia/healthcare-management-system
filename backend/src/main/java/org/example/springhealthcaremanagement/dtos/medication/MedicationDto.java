package org.example.springhealthcaremanagement.dtos.medication;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {
    private Long id;
    private String name;
    private String description;
    private String dosage;
    private String frequency;

}
