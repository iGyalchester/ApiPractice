package com.example.NotADemo.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

//java -jar notademo-001-SNAPSHOT.jar --server.port=8080 to change port
//GET http://localhost:8080/api/v1/student
/*
change student info
PUT http://localhost:8080/api/v1/student/3
Accept: application/json
Content-Type: application/json

{
  "name": "John Doe",
  "email": "boris@gmail.com",
  "dob": "1994-10-03"
}
 */

/*
delete student
DELETE http://localhost:8080/api/v1/student/3
 */

/*
PUT http://localhost:8080/api/v1/student/115?name=Jonny Sins&email=jonnysins@gmail.com&dob=1997-12-22
Accept: application/json
Content-Type: application/json
 */

@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    @Transient//auto calculate age
    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
