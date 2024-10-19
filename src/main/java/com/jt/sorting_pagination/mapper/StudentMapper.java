package com.jt.sorting_pagination.mapper;

import org.springframework.beans.BeanUtils;

import com.jt.sorting_pagination.dto.StudentDTO;
import com.jt.sorting_pagination.model.Student;

public class StudentMapper {

    public static Student convertToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        return student;
    }

    public static StudentDTO convertToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }

}
