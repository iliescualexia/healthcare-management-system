package org.example.springhealthcaremanagement.dtos.medication;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationRequestDto {
    private String name;
    private String description;
    private String dosage;
    private String frequency;
}
