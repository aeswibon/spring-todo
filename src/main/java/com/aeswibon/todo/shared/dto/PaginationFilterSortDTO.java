package com.aeswibon.todo.shared.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationFilterSortDTO {
    @Min(value = 1, message = "Page number should be greater than 0")
    int pageSize = 10;
    @Min(value = 1, message = "Page number should be greater than 0")
    int pageNumber = 1;
    String sortBy = null;
}
