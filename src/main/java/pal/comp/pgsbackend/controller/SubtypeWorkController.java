package pal.comp.pgsbackend.controller;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.subtypework.SubtypeWorkDto;
import pal.comp.pgsbackend.dto.subtypework.UpdateSubtypeWorkDto;
import pal.comp.pgsbackend.services.SubtypeWorkService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/subtype-work")
public class SubtypeWorkController {
    private static final Logger log = LoggerFactory.getLogger(SubtypeWorkController.class);
    private final SubtypeWorkService subtypeWorkService;

    public SubtypeWorkController(SubtypeWorkService subtypeWorkService) {
        this.subtypeWorkService = subtypeWorkService;
    }

    @GetMapping
    public List<SubtypeWorkDto> getAll() {
        log.info("Called get all subtype work controller");
        return this.subtypeWorkService.getAll();
    }

    @GetMapping("/with-filters")
    public List<SubtypeWorkDto> getAllWithFilters(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "typeWorkId", required = false) Long typeWorkId,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        log.info("Called get all with filters subtype work controller");
        return this.subtypeWorkService.getAllWithFilters(code, name, typeWorkId, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public SubtypeWorkDto getById(@PathVariable Long id) {
        log.info("Called get by id subtype work controller");
        return this.subtypeWorkService.getById(id);
    }

    @GetMapping("/by-type-work/{id}")
    public List<SubtypeWorkDto> getByTypeWorkId(@PathVariable Long id) {
        log.info("Called get by type work id subtype work controller");
        return this.subtypeWorkService.getByTypeWorkId(id);
    }

    @GetMapping("/by-name")
    public SubtypeWorkDto getByName(@RequestParam String name) {
        log.info("Called get by name subtype work controller");
        return this.subtypeWorkService.getByName(name);
    }

    @GetMapping("/planing")
    public List<SubtypeWorkDto> getPlaningSubtypeWork(
            @RequestParam(value = "plotId", required = true) Long plotId,
            @RequestParam(value = "typeWorkId", required = true) Long typeWorkId
    ) {
        log.info("Called get planing subtype work controller");
        return this.subtypeWorkService.getPlaningSubtypeWork(plotId, typeWorkId);
    }


    @PostMapping
    public SubtypeWorkDto create(@RequestBody @Valid SubtypeWorkDto subtypeWorkToCreate) {
        log.info("Called create subtype work controller");
        return this.subtypeWorkService.create(subtypeWorkToCreate);
    }

    @PatchMapping("/{id}")
    public SubtypeWorkDto update(@PathVariable Long id, @RequestBody @Valid UpdateSubtypeWorkDto subtypeWorkToUpdate) {
        log.info("Called update subtype work controller");
        return this.subtypeWorkService.update(id, subtypeWorkToUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Called delete subtype work controller");
        this.subtypeWorkService.delete(id);
    }
}
