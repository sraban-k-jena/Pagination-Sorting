package com.jt.sorting_pagination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jt.sorting_pagination.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
