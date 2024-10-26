package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.TreatmentInfo;
import com.aiomed.treatmentplan.service.TreatmentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("treatment-info")
public class TreatmentInfoController {

    private final TreatmentInfoService treatmentInfoService;

    @PostMapping("create")
    public ResponseEntity<TreatmentInfo> create(@RequestBody TreatmentInfo treatmentInfo) {
        TreatmentInfo result = treatmentInfoService.create(treatmentInfo);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("find-by-id")
    public ResponseEntity<TreatmentInfo> findById(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(treatmentInfoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<TreatmentInfo>> findAll() {
        return new ResponseEntity<>(treatmentInfoService.findAll(), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<TreatmentInfo> update(@RequestBody TreatmentInfo treatmentInfo) {
        TreatmentInfo result = treatmentInfoService.update(treatmentInfo);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        treatmentInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
