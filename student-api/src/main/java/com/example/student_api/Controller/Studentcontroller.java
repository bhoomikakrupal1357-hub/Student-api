package com.example.student_api.Controller;

import com.example.student_api.entity.Student;
import com.example.student_api.Service.Studentservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class Studentcontroller {

    private final Studentservice service;

    public Studentcontroller(Studentservice service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student savedStudent = service.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping
    public List<Student> getAll(@RequestParam(required = false) String course) {
        return service.getAllStudents(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(
            @PathVariable Long id,
            @RequestBody Student student) {

        return service.update(id, student)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}