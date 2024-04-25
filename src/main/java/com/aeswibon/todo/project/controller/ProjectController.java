package com.aeswibon.todo.project.controller;

import com.aeswibon.todo.project.dto.ProjectCreateDTO;
import com.aeswibon.todo.project.dto.ProjectFullDTO;
import com.aeswibon.todo.project.dto.ProjectUpdateDTO;
import com.aeswibon.todo.project.dto.ProjectsResponseDTO;
import com.aeswibon.todo.project.service.ProjectService;
import com.aeswibon.todo.shared.dto.MessageResponseDTO;
import com.aeswibon.todo.shared.dto.PaginationFilterSortDTO;
import com.aeswibon.todo.shared.dto.SuccessResponseDTO;
import com.aeswibon.todo.shared.exception.AppException;
import com.aeswibon.todo.shared.utils.ResponseHelper;
import com.aeswibon.todo.shared.utils.Routes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(Routes.Project)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ResponseHelper responseHelper;

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<ProjectsResponseDTO>> findAllProjects(
            @RequestParam PaginationFilterSortDTO paginationFilterSortDTO
    ) throws AppException {
        ProjectsResponseDTO result = projectService.findAllProjects(paginationFilterSortDTO);
        return responseHelper.getSuccessResponse("project", result);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<SuccessResponseDTO<ProjectFullDTO>> findProject(
            @PathVariable("uuid") String projectUuid
    ) throws AppException {
        ProjectFullDTO result = projectService.findProject(projectUuid);
        return responseHelper.getSuccessResponse("project", result);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<MessageResponseDTO>> createProject(
            @RequestBody ProjectCreateDTO request
    ) throws AppException {
        MessageResponseDTO result = projectService.createProject(request);
        return responseHelper.getSuccessResponse("project", result);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<SuccessResponseDTO<MessageResponseDTO>> updateProject(
            @PathVariable("uuid") String projectUuid,
            @RequestBody ProjectUpdateDTO request
    ) throws AppException {
        MessageResponseDTO result = projectService.updateProject(projectUuid, request);
        return responseHelper.getSuccessResponse("project", result);
    }

}