package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.users.RequestUserDto;
import pal.comp.pgsbackend.dto.users.ResponseUserDto;
import pal.comp.pgsbackend.services.UserService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService projectManagerService;

    public UserController(UserService projectManagerService) {
        this.projectManagerService = projectManagerService;
    }

    @GetMapping
    public List<ResponseUserDto> getAll() {
        return projectManagerService.getAll();
    }

    @GetMapping("/{telegramId}")
    public ResponseUserDto getByTelegramId(@PathVariable String telegramId) {
        return projectManagerService.getByTelegramId(telegramId);
    }

    @PostMapping
    public ResponseUserDto create(@RequestBody @Valid RequestUserDto projectManagerToCreate) {
        return projectManagerService.create(projectManagerToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectManagerService.delete(id);
    }
}
