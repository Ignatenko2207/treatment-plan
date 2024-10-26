package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.User;
import com.aiomed.treatmentplan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping("create")
    public ResponseEntity<User> create(@RequestBody User user) {
        User result = userService.create(user);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("find-by-id")
    public ResponseEntity<User> findById(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User result = userService.update(user);
        if (Objects.nonNull(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam("id") Integer id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
