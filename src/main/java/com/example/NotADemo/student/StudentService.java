package com.example.NotADemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        //throws exception if student email already exists
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalArgumentException("Student with this email already exists");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (exists) {
            studentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Student with this id does not exist");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email, LocalDate dob) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("Student with this id does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);
        }

        if (dob != null && !Objects.equals(student.getDob(), dob)) {
            student.setDob(dob);
        }

    }
}
