package com.example.service_b.service;

import com.example.service_b.model.Student;
import com.example.service_b.repository.StudentRepository;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public Student saveSingle(Student student) {
        return studentRepository.save(student);
    }


    public List<Student> saveBatch(List<Student> students) {
        List<Student> toSave = new ArrayList<>();

        for (Student student : students) {
            // skip duplicate emails
            if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
                continue;
            }
            toSave.add(student);
        }

        List<Student> saved = studentRepository.saveAll(toSave);
        return saved;
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found" + id));
    }

    public String deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id" + id);
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully with id" + id;
    }


    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id" + id));

        if (updatedStudent.getName() != null) {
            existing.setName(updatedStudent.getName());
        }
        if (updatedStudent.getEmail() != null) {
            existing.setEmail(updatedStudent.getEmail());
        }
        if (updatedStudent.getAge() != null) {
            existing.setAge(updatedStudent.getAge());
        }
        if (updatedStudent.getDepartment() != null) {
            existing.setDepartment(updatedStudent.getDepartment());
        }
        if (updatedStudent.getPhone() != null) {
            existing.setPhone(updatedStudent.getPhone());
        }

        return studentRepository.save(existing);
    }
}