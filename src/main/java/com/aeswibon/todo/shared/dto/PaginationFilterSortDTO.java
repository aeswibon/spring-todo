package com.aeswibon.todo.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationFilterSortDTO {
    int pageSize = 10;
    int pageNumber = 1;
//    String searchText = null;
    String sortBy = null;
}
