package com.example.demo.service;

import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public List<Teacher> getAllTeachers() {
        return repository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return repository.findById(id);
    }

    public Teacher addTeacher(Teacher teacher) {
        return repository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        return repository.findById(id).map(t -> {
            t.setName(updatedTeacher.getName());
            t.setSubject(updatedTeacher.getSubject());
            return repository.save(t);
        }).orElse(null);
    }

    public void deleteTeacher(Long id) {
        repository.deleteById(id);
    }
}
