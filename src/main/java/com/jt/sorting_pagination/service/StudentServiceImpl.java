package com.jt.sorting_pagination.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jt.sorting_pagination.dto.StudentDTO;
import com.jt.sorting_pagination.dto.StudentResponseDTO;
import com.jt.sorting_pagination.mapper.StudentMapper;
import com.jt.sorting_pagination.model.Student;
import com.jt.sorting_pagination.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.convertToStudent(studentDTO);
        studentRepo.save(student);
        return StudentMapper.convertToStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStduent() {

        List<Student> students = (List<Student>) studentRepo.findAll();
        return students.stream().map(StudentMapper::convertToStudentDTO).collect(Collectors.toList());

    }

    @Override
    public StudentDTO getStudentById(int id) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalArgumentException(id + " does not exists .");
        }
        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Server not connected ."));
        return StudentMapper.convertToStudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalArgumentException(id + "id is not found");
        }
        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Server not Found ."));
        BeanUtils.copyProperties(studentDTO, student, "id");
        studentRepo.save(student);
        return StudentMapper.convertToStudentDTO(student);
    }

    @Override
    public void deleteStudentById(int id) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalArgumentException(id + "id is not found");
        }
        studentRepo.deleteById(id);
    }

    @Override
    public StudentDTO updatePrice(int id, double price) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalArgumentException(id + " does not exists .");
        }
        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Internal server Error ."));
        student.setPrice(price + student.getPrice());
        studentRepo.save(student);
        return StudentMapper.convertToStudentDTO(student);
    }

    @Override
    public StudentResponseDTO getStudentWithPagination(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> page = studentRepo.findAll(pageable);
        List<StudentDTO> studentDTOs = page.getContent().stream().map(StudentMapper::convertToStudentDTO)
                .collect(Collectors.toList());

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudents(studentDTOs);
        studentResponseDTO.setFirst(page.isFirst());
        studentResponseDTO.setLast(page.isLast());
        studentResponseDTO.setTotalElements(page.getTotalElements());
        studentResponseDTO.setTotalPage(page.getTotalPages());
        studentResponseDTO.setPageNo(page.getNumber());
        studentResponseDTO.setPageSize(page.getSize());

        return studentResponseDTO;
    }

}
