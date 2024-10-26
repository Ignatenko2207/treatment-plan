package com.aiomed.treatmentplan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "treatment_info")
public class TreatmentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // ActionA, ActionB, etc.
    // It's better to have it in DB instead of hardcoded in enum
    @Column(name = "description", nullable = false)
    private String description;

    // if null or zero, it can be considered as one-time action
    @Column(name = "duration")
    private Long duration;

}
