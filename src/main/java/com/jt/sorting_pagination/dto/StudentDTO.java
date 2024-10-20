package com.jt.sorting_pagination.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class StudentDTO {

    private int id;

    @NotBlank
    private String name;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Description must be between 3 and 10 characters")
    private String description;

    @Min(value = 0, message = "Price must be positive")
    private double price;

    private int quantity;
}
