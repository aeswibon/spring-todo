package com.aeswibon.todo.project.service;

import com.aeswibon.todo.project.adapter.ProjectAdapter;
import com.aeswibon.todo.project.db.entity.Project;
import com.aeswibon.todo.project.db.repository.ProjectRepository;
import com.aeswibon.todo.project.dto.*;
import com.aeswibon.todo.shared.adapter.AppExceptionAdapter;
import com.aeswibon.todo.shared.constants.Constants;
import com.aeswibon.todo.shared.constants.ErrorConstants;
import com.aeswibon.todo.shared.dto.MessageResponseDTO;
import com.aeswibon.todo.shared.dto.PaginationFilterSortDTO;
import com.aeswibon.todo.shared.exception.AppException;
import com.aeswibon.todo.shared.utils.PagingHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private Project _findProject(String projectId) throws AppException {
        Optional<Project> project = projectRepository.findById(UUID.fromString(projectId));
        if (project.isEmpty()) {
            throw AppExceptionAdapter.getExceptionObjectFrom(ErrorConstants.RESOURCE_NOT_FOUND);
        }
        return project.get();
    }

    @Override
    public MessageResponseDTO createProject(ProjectCreateDTO request) throws AppException {
        Optional<Project> project = projectRepository.findByTitle(request.getTitle());
        if (project.isPresent()) {
            throw AppExceptionAdapter.getExceptionObjectFrom(ErrorConstants.RESOURCE_ALREADY_EXISTS);
        }

        Project newProject = Project.builder()
                .title(request.getTitle())
                .build();
        projectRepository.save(newProject);
        return MessageResponseDTO.builder().message(Constants.RESOURCE_CREATED_SUCCESS_MESSAGE).build();
    }

    @Override
    public MessageResponseDTO updateProject(String projectId, ProjectUpdateDTO request) throws AppException {
        Project existingProject = _findProject(projectId);
        existingProject.setTitle(request.getTitle());
        projectRepository.save(existingProject);
        return MessageResponseDTO.builder().message(Constants.RESOURCE_UPDATED_SUCCESS_MESSAGE).build();
    }

    @Override
    public ProjectFullDTO findProject(String projectId) throws AppException {
        Project project = _findProject(projectId);
        return ProjectAdapter.getCompleteProject(project);
    }

    @Override
    public ProjectsResponseDTO findAllProjects(PaginationFilterSortDTO data) throws AppException {
        Pageable paging = PagingHelper.getPagingObject(data.getPageNumber(), data.getPageSize(), data.getSortBy());
        Page<Project> query = projectRepository.findAll(paging);
        List<ProjectDTO> projects = new ArrayList<>();
        query.forEach(project -> projects.add(ProjectAdapter.getProject(project)));
        return ProjectsResponseDTO.builder().count((long) projects.size()).projects(projects).build();
    }
}
