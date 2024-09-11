package com.drive.student.model;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class DBInit implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (studentRepo.count() > 0) {
            log.info("Data already exists");
            return;
        }

        try {
            // Create Student 1
            Student student1 = new Student();
            student1.setFirstName("Arun");
            student1.setLastName("Kumar");
            student1.setPassword(passwordEncoder.encode("password123"));
            student1.setGender(Student.Gender.MALE);
            student1.setDob(LocalDate.of(2004, 5, 1));
            student1.setPhoneNumber("9876543210");
            student1.setEmail("arun.kumar@example.com");
            student1.setMark10th(88.5);
            student1.setMark12th(90.0);
            student1.setUgMark(78.0);
            student1.setBacklog(0);
            student1.setActiveBacklog(0);
            student1.setInterestedCourse("Computer Science");

            studentRepo.save(student1);

            // Create Student 2
            Student student2 = new Student();
            student2.setFirstName("Meena");
            student2.setLastName("Ravi");
            student2.setPassword(passwordEncoder.encode("password456"));
            student2.setGender(Student.Gender.FEMALE);
            student2.setDob(LocalDate.of(2003, 11, 12));
            student2.setPhoneNumber("8765432109");
            student2.setEmail("meena.ravi@example.com");
            student2.setMark10th(92.5);
            student2.setMark12th(94.0);
            student2.setUgMark(85.0);
            student2.setBacklog(0);
            student2.setActiveBacklog(0);
            student2.setInterestedCourse("Biotechnology");

            studentRepo.save(student2);

            // Create Student 3
            Student student3 = new Student();
            student3.setFirstName("Vijay");
            student3.setLastName("Raj");
            student3.setPassword(passwordEncoder.encode("password789"));
            student3.setGender(Student.Gender.MALE);
            student3.setDob(LocalDate.of(2004, 2, 15));
            student3.setPhoneNumber("7654321098");
            student3.setEmail("vijay.raj@example.com");
            student3.setMark10th(85.0);
            student3.setMark12th(87.5);
            student3.setUgMark(75.0);
            student3.setBacklog(0);
            student3.setActiveBacklog(0);
            student3.setInterestedCourse("Mechanical Engineering");

            studentRepo.save(student3);

            // Create Student 4
            Student student4 = new Student();
            student4.setFirstName("Priya");
            student4.setLastName("Sundar");
            student4.setPassword(passwordEncoder.encode("password321"));
            student4.setGender(Student.Gender.FEMALE);
            student4.setDob(LocalDate.of(2005, 8, 20));
            student4.setPhoneNumber("6543210987");
            student4.setEmail("priya.sundar@example.com");
            student4.setMark10th(90.0);
            student4.setMark12th(92.0);
            student4.setUgMark(80.0);
            student4.setBacklog(1);
            student4.setActiveBacklog(0);
            student4.setInterestedCourse("Electronics");

            studentRepo.save(student4);

            // Create Student 5
            Student student5 = new Student();
            student5.setFirstName("Saravanan");
            student5.setLastName("Balan");
            student5.setPassword(passwordEncoder.encode("password654"));
            student5.setGender(Student.Gender.MALE);
            student5.setDob(LocalDate.of(2003, 4, 18));
            student5.setPhoneNumber("5432109876");
            student5.setEmail("saravanan.balan@example.com");
            student5.setMark10th(82.0);
            student5.setMark12th(85.0);
            student5.setUgMark(72.5);
            student5.setBacklog(0);
            student5.setActiveBacklog(0);
            student5.setInterestedCourse("Civil Engineering");

            studentRepo.save(student5);

            // Create Student 6
            Student student6 = new Student();
            student6.setFirstName("Radha");
            student6.setLastName("Venkat");
            student6.setPassword(passwordEncoder.encode("password852"));
            student6.setGender(Student.Gender.FEMALE);
            student6.setDob(LocalDate.of(2005, 6, 30));
            student6.setPhoneNumber("4321098765");
            student6.setEmail("radha.venkat@example.com");
            student6.setMark10th(89.0);
            student6.setMark12th(90.5);
            student6.setUgMark(77.0);
            student6.setBacklog(0);
            student6.setActiveBacklog(0);
            student6.setInterestedCourse("Mathematics");

            studentRepo.save(student6);

            // Create Student 7
            Student student7 = new Student();
            student7.setFirstName("Karthik");
            student7.setLastName("Shankar");
            student7.setPassword(passwordEncoder.encode("password963"));
            student7.setGender(Student.Gender.MALE);
            student7.setDob(LocalDate.of(2004, 7, 12));
            student7.setPhoneNumber("3210987654");
            student7.setEmail("karthik.shankar@example.com");
            student7.setMark10th(87.0);
            student7.setMark12th(89.0);
            student7.setUgMark(80.0);
            student7.setBacklog(0);
            student7.setActiveBacklog(0);
            student7.setInterestedCourse("Information Technology");

            studentRepo.save(student7);

            // Create Student 8
            Student student8 = new Student();
            student8.setFirstName("Divya");
            student8.setLastName("Murugan");
            student8.setPassword(passwordEncoder.encode("password741"));
            student8.setGender(Student.Gender.FEMALE);
            student8.setDob(LocalDate.of(2003, 9, 25));
            student8.setPhoneNumber("2109876543");
            student8.setEmail("divya.murugan@example.com");
            student8.setMark10th(92.0);
            student8.setMark12th(93.5);
            student8.setUgMark(82.5);
            student8.setBacklog(0);
            student8.setActiveBacklog(0);
            student8.setInterestedCourse("Physics");

            studentRepo.save(student8);

            // Create Student 9
            Student student9 = new Student();
            student9.setFirstName("Ramesh");
            student9.setLastName("Ganesh");
            student9.setPassword(passwordEncoder.encode("password159"));
            student9.setGender(Student.Gender.MALE);
            student9.setDob(LocalDate.of(2004, 10, 5));
            student9.setPhoneNumber("1098765432");
            student9.setEmail("ramesh.ganesh@example.com");
            student9.setMark10th(88.0);
            student9.setMark12th(89.5);
            student9.setUgMark(78.0);
            student9.setBacklog(0);
            student9.setActiveBacklog(0);
            student9.setInterestedCourse("Chemistry");

            studentRepo.save(student9);

            // Create Student 10
            Student student10 = new Student();
            student10.setFirstName("Lakshmi");
            student10.setLastName("Mani");
            student10.setPassword(passwordEncoder.encode("password357"));
            student10.setGender(Student.Gender.FEMALE);
            student10.setDob(LocalDate.of(2005, 3, 21));
            student10.setPhoneNumber("0987654321");
            student10.setEmail("lakshmi.mani@example.com");
            student10.setMark10th(91.0);
            student10.setMark12th(92.5);
            student10.setUgMark(83.0);
            student10.setBacklog(0);
            student10.setActiveBacklog(0);
            student10.setInterestedCourse("English");

            studentRepo.save(student10);

            log.info("All students added successfully");

        } catch (Throwable e) {
            log.error("Something went wrong while inserting default records", e);
        }
    }
}
