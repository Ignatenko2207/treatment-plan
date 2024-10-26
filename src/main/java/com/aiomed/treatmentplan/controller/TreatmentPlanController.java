package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.TreatmentPlan;
import com.aiomed.treatmentplan.service.TreatmentPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("treatment-plan")
public class TreatmentPlanController {

    private final TreatmentPlanService treatmentPlanService;

    @PostMapping("create")
    public ResponseEntity<TreatmentPlan> create(@RequestBody TreatmentPlan treatmentPlan) {
        TreatmentPlan result = treatmentPlanService.create(treatmentPlan);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("find-by-id")
    public ResponseEntity<TreatmentPlan> findById(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(treatmentPlanService.findById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<TreatmentPlan> update(@RequestBody TreatmentPlan treatmentPlan) {
        TreatmentPlan result = treatmentPlanService.update(treatmentPlan);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        treatmentPlanService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
