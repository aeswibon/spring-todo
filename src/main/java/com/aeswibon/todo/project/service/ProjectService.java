package com.aeswibon.todo.project.service;

import com.aeswibon.todo.project.dto.ProjectCreateDTO;
import com.aeswibon.todo.project.dto.ProjectFullDTO;
import com.aeswibon.todo.project.dto.ProjectUpdateDTO;
import com.aeswibon.todo.project.dto.ProjectsResponseDTO;
import com.aeswibon.todo.shared.dto.MessageResponseDTO;
import com.aeswibon.todo.shared.dto.PaginationFilterSortDTO;
import com.aeswibon.todo.shared.exception.AppException;

public interface ProjectService {
    MessageResponseDTO createProject(ProjectCreateDTO request) throws AppException;
    MessageResponseDTO updateProject(String projectId, ProjectUpdateDTO request) throws AppException;
    ProjectFullDTO findProject(String projectId) throws AppException;
    ProjectsResponseDTO findAllProjects(PaginationFilterSortDTO data) throws AppException;
}
