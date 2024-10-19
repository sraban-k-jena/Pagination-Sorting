package com.jt.sorting_pagination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.sorting_pagination.dto.StudentDTO;
import com.jt.sorting_pagination.dto.StudentResponseDTO;
import com.jt.sorting_pagination.model.Student;
import com.jt.sorting_pagination.service.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    public StudentService service;

    @PostMapping("/addStudent")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO studentDTO2 = service.addStudent(studentDTO);
        return new ResponseEntity<>(studentDTO2, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        List<StudentDTO> studentDTOs = service.getAllStduent();
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        StudentDTO studentDTO = service.getStudentById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        StudentDTO studentDTO2 = service.updateStudent(id, studentDTO);
        return new ResponseEntity<>(studentDTO2, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable int id) {
        service.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/update/{price}")
    public ResponseEntity<StudentDTO> updatePrice(@PathVariable int id, @PathVariable double price) {
        StudentDTO studentDTO = service.updatePrice(id, price);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping("/pagignation/{pageNo}/{pageSize}/{sortBy}/{sortDir}")
    public ResponseEntity<StudentResponseDTO> pagination(@PathVariable int pageNo,
            @PathVariable int pageSize,
            @PathVariable String sortBy,
            @PathVariable String sortDir) {
        StudentResponseDTO studentDTO = service.getStudentWithPagination(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
}
