package com.jt.sorting_pagination.service;

import java.util.List;

import com.jt.sorting_pagination.dto.StudentDTO;
import com.jt.sorting_pagination.dto.StudentResponseDTO;

public interface StudentService {

    StudentDTO addStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStduent();

    StudentDTO getStudentById(int id);

    StudentDTO updateStudent(int id, StudentDTO studentDTO);

    void deleteStudentById(int id);

    StudentDTO updatePrice(int id, double price);

    StudentResponseDTO getStudentWithPagination(int pageNo, int pageSize, String sortBy, String sortDir);
}
