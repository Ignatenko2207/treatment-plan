package com.aiomed.treatmentplan.model;

import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "treatment_id", nullable = false)
    private Integer treatmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TreatmentTaskStatus status;

}
