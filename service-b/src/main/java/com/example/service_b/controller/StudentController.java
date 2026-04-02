package com.example.service_b.controller;

import com.example.service_b.model.Student;
import com.example.service_b.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(
                studentService.saveSingle(student),
                HttpStatus.CREATED
        );
    }


    @PostMapping("/batch")
    public ResponseEntity<List<Student>> createBatch(@RequestBody List<Student> students) {
        return new ResponseEntity<>(
                studentService.saveBatch(students),
                HttpStatus.CREATED
        );
    }


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(
                studentService.getAllStudents(),
                HttpStatus.OK
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(
                studentService.getStudentById(id),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return new ResponseEntity<>(
                studentService.deleteStudent(id),
                HttpStatus.OK
        );
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student updatedStudent) {
        return new ResponseEntity<>(
                studentService.updateStudent(id, updatedStudent),
                HttpStatus.OK
        );
    }

}