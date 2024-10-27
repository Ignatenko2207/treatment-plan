package com.aiomed.treatmentplan.model;

import com.aiomed.treatmentplan.model.enums.TreatmentPlanStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "treatment_plan")
public class TreatmentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    // if null, it can be considered as one-time action
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany
    private List<Treatment> treatments;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TreatmentPlanStatus status;

}
