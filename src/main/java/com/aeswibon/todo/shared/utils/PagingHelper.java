package com.aeswibon.todo.shared.utils;

import com.aeswibon.todo.shared.adapter.AppExceptionAdapter;
import com.aeswibon.todo.shared.error.AppErrorConstants;
import com.aeswibon.todo.shared.exception.AppException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PagingHelper {
    public static PageRequest getPagingObject(int pageNumber, int pageSize, String sortBy) throws AppException {
        if(pageNumber < 1 || pageSize < 1) {
            throw AppExceptionAdapter.getExceptionObjectFrom(AppErrorConstants.INTERNAL_SERVER_ERROR);
        }
        if(sortBy == null) {
            return PageRequest.of(
                    (pageNumber - 1),
                    pageSize
            );
        }
        return PageRequest.of(
                (pageNumber - 1),
                pageSize,
                Sort.by(sortBy).descending()
        );
    }
}
