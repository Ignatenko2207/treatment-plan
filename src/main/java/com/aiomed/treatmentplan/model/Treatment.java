package com.aiomed.treatmentplan.model;

import com.aiomed.treatmentplan.model.enums.TreatmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    private User user;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    // if null, it can be considered as one-time action
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "cron_expression")
    private String cronExpression;

    @ManyToOne
    private TreatmentInfo treatmentInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TreatmentStatus status;

}
