package com.example.NotADemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    //student service will be instantiated for us and then injected into the controller
    //also tell that student service is a bean by going into StudentService class and adding @Component/@Service at the top.
    //@Service is preferred because it is more for readability.
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent() {
        return studentService.getStudents();
    }

    @PostMapping
    //@RequestBody tells spring to get the body of the request and convert it to a student object
    public void registerNewStudent(@RequestBody Student student) {
    	studentService.addNewStudent(student);
    }

    //delete student
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    //update student
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob) {
        studentService.updateStudent(studentId, name, email, dob);
    }

}
