package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.projectmanager.ResponseProjectManagerDto;
import pal.comp.pgsbackend.services.ProjectManagerService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/project-manager")
public class ProjectManagerController {
    private final ProjectManagerService projectManagerService;

    public ProjectManagerController(ProjectManagerService projectManagerService) {
        this.projectManagerService = projectManagerService;
    }

    @GetMapping
    public List<ResponseProjectManagerDto> getAll() {
        return projectManagerService.getAll();
    }

    @GetMapping("/{telegramId}")
    public ResponseProjectManagerDto getByTelegramId(@PathVariable String telegramId) {
        return projectManagerService.getByTelegramId(telegramId);
    }

    @PostMapping
    public ResponseProjectManagerDto create(@RequestBody @Valid ResponseProjectManagerDto projectManagerToCreate) {
        return projectManagerService.create(projectManagerToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectManagerService.delete(id);
    }
}
