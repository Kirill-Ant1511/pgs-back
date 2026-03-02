package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.plot.RequestAddPlotForUserDto;
import pal.comp.pgsbackend.dto.users.RequestUpdateUserDto;
import pal.comp.pgsbackend.dto.users.RequestUserDto;
import pal.comp.pgsbackend.dto.users.ResponseUserDto;
import pal.comp.pgsbackend.services.UserService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService projectManagerService) {
        this.userService = projectManagerService;
    }

    @GetMapping
    public List<ResponseUserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{telegramId}")
    public ResponseUserDto getByTelegramId(@PathVariable String telegramId) {
        return userService.getByTelegramId(telegramId);
    }

    @PostMapping
    public ResponseUserDto create(@RequestBody @Valid RequestUserDto projectManagerToCreate) {
        return userService.create(projectManagerToCreate);
    }

    @PostMapping("/add-plot")
    public ResponseUserDto addPlot(@RequestBody @Valid RequestAddPlotForUserDto requestDto) {
        return userService.addPlotForUser(requestDto.userId(), requestDto.plotId());
    }

    @PatchMapping("/{id}")
    public ResponseUserDto update(@PathVariable Long id, @RequestBody @Valid RequestUpdateUserDto requestUpdateDto) {
        return this.userService.update(id, requestUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
