package com.example.service_b.service;

import com.example.service_b.model.Student;
import com.example.service_b.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testSaveSingle() {
        Student student = new Student(null, "Santhosh", "test@gmail.com", 21, "IT", "9999999999");

        Mockito.when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.saveSingle(student);

        assertEquals("Santhosh", result.getName());
    }

    @Test
    void testGetAllStudents() {
        List<Student> list = List.of(
                new Student(1L, "Santhosh", "test@gmail.com", 21, "IT", "999")
        );

        Mockito.when(studentRepository.findAll()).thenReturn(list);

        List<Student> result = studentService.getAllStudents();

        assertEquals(1, result.size());
    }

    @Test
    void testGetStudentById() {
        Student student = new Student(1L, "Santhosh", "test@gmail.com", 21, "IT", "999");
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);
        assertEquals("Santhosh", result.getName());
    }

    @Test
    void testDeleteStudent() {
        Mockito.when(studentRepository.existsById(1L)).thenReturn(true);

        String result = studentService.deleteStudent(1L);
        assertTrue(result.contains("deleted"));
    }

    @Test
    void testUpdateStudent() {
        Student existing = new Student(1L, "Old", "old@gmail.com", 20, "CSE", "111");
        Student updated = new Student(null, "New", null, null, null, null);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(existing));
        Mockito.when(studentRepository.save(existing)).thenReturn(existing);

        Student result = studentService.updateStudent(1L, updated);
        assertEquals("New", result.getName());
    }

    @Test
    void testSaveBatch_skipDuplicates() {
        Student s1 = new Student(null, "A", "a@gmail.com", 20, "IT", "111");
        Student s2 = new Student(null, "B", "b@gmail.com", 21, "IT", "222");

        Mockito.when(studentRepository.findByEmail("a@gmail.com")).thenReturn(Optional.of(s1));//duplicate
        Mockito.when(studentRepository.findByEmail("b@gmail.com")).thenReturn(Optional.empty());

        Mockito.when(studentRepository.saveAll(List.of(s2))).thenReturn(List.of(s2));

        List<Student> result = studentService.saveBatch(List.of(s1, s2));

        assertEquals(1, result.size());
        assertEquals("B", result.get(0).getName());
    }
}