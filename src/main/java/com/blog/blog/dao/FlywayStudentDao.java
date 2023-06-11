package com.blog.blog.dao;

import com.blog.blog.flyway.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlywayStudentDao extends JpaRepository<Student,Integer> {
}
