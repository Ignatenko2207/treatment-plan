package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.service.TreatmentTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("treatment-task")
public class TreatmentTaskController {

    private final TreatmentTaskService treatmentTaskService;

    @PostMapping("create")
    public ResponseEntity<TreatmentTask> create(@RequestBody TreatmentTask treatmentTask) {
        TreatmentTask result = treatmentTaskService.create(treatmentTask);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("find-by-id")
    public ResponseEntity<TreatmentTask> findById(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(treatmentTaskService.findById(id), HttpStatus.OK);
    }

    @GetMapping("find-by-parameters")
    public ResponseEntity<List<TreatmentTask>> findById(
            @RequestParam("userId") Integer userId,
            @RequestParam("startTime") String startTime
    ) {
        return new ResponseEntity<>(treatmentTaskService.findAllByUserAndStartTime(userId, startTime), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<TreatmentTask> update(@RequestBody TreatmentTask treatmentTask) {
        TreatmentTask result = treatmentTaskService.update(treatmentTask);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        treatmentTaskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
