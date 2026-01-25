package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.typework.TypeWorkDto;
import pal.comp.pgsbackend.dto.typework.UpdateTypeWorkDto;
import pal.comp.pgsbackend.services.TypeWorkService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/type-work")
public class TypeWorkController {

    private static final Logger log = LoggerFactory.getLogger(TypeWorkController.class);
    private final TypeWorkService typeWorkService;

    public TypeWorkController(TypeWorkService typeWorkService) {
        this.typeWorkService = typeWorkService;
    }

    @GetMapping
    public List<TypeWorkDto> getAll() {
        log.info("Called get all type work controller");
        return this.typeWorkService.getAll();
    }

    @GetMapping("/{id}")
    public TypeWorkDto getById(@PathVariable Long id) {
        log.info("Called get by id type work controller");
        return this.typeWorkService.getById(id);
    }

    @GetMapping("/by-name")
    public TypeWorkDto getByName(@RequestParam String name) {
        log.info("Called get by name type work controller");
        return this.typeWorkService.getByName(name);
    }

    @GetMapping("/planing")
    public List<TypeWorkDto> getPlaningTypeWork(@RequestParam(value = "plotId", required = true) Long plotId) {
        log.info("Called get planing type work controller");
        return this.typeWorkService.getPlaningTypeWork(plotId);
    }

    @PostMapping
    public TypeWorkDto create(@RequestBody @Valid TypeWorkDto typeWorkToCreate) {
        log.info("Called create type work controller");
        return this.typeWorkService.create(typeWorkToCreate);
    }

    @PatchMapping("/{id}")
    public TypeWorkDto update(@PathVariable Long id, @RequestBody @Valid UpdateTypeWorkDto typeWorkToUpdate) {
        log.info("Called update type work controller");
        return this.typeWorkService.update(id, typeWorkToUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Called delete type work controller");
        this.typeWorkService.delete(id);
    }
}
