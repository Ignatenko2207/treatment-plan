package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.Treatment;
import com.aiomed.treatmentplan.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("treatment")
public class TreatmentController {

    private final TreatmentService treatmentService;

    @PostMapping("create")
    public ResponseEntity<Treatment> create(@RequestBody Treatment treatment) {
        Treatment result = treatmentService.create(treatment);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("find-by-id")
    public ResponseEntity<Treatment> findById(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(treatmentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Treatment> update(@RequestBody Treatment treatment) {
        Treatment result = treatmentService.update(treatment);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        treatmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
