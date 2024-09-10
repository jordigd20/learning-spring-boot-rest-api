package com.rest.crud.rest_crud.students.controller;

import com.rest.crud.rest_crud.students.entity.Student;
import com.rest.crud.rest_crud.common.errors.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students;

    @PostConstruct
    public void loadData() {
        this.students = new ArrayList<Student>();

        this.students.add(new Student("Jordi", "Gómez"));
        this.students.add(new Student("Pedro", "Roncero"));
        this.students.add(new Student("David", "Bisbal"));
    }

    @GetMapping
    public List<Student> getStudents() {
        return this.students;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        if (studentId >= this.students.size() || studentId < 0) {
            throw new NotFoundException("Student id not found - " + studentId);
        }

        return this.students.get(studentId);
    }

}
