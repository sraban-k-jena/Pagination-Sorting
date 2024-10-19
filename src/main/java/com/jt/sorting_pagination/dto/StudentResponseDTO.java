package com.jt.sorting_pagination.dto;

import java.util.List;

import lombok.Data;

@Data
public class StudentResponseDTO {

    private List<StudentDTO> students;
    private long totalElements;
    private int totalPage;
    private boolean isFirst;
    private boolean isLast;
    private int pageNo;
    private int pageSize;
}
