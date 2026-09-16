package com.example.student_api.Service;

import com.example.student_api.entity.Student;
import com.example.student_api.repository.Studentrepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Studentservice {

    private final Studentrepo repository;

    public Studentservice(Studentrepo repository) {
        this.repository = repository;
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudents(String course) {
        if (course != null) {
            return repository.findByCourse(course);
        }
        return repository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Student> update(Long id, Student updatedStudent) {
        return repository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setCourse(updatedStudent.getCourse());
            student.setAge(updatedStudent.getAge());

            return repository.save(student);
        });
    }

    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}