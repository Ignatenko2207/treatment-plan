package com.aiomed.treatmentplan.model;

import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "treatment_task")
public class TreatmentTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    private User user;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    // if null, it can be considered as one-time action
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    private TreatmentInfo treatmentInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TreatmentTaskStatus status;

}
