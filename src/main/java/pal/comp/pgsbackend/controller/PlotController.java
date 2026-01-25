package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.plot.PlotDto;
import pal.comp.pgsbackend.services.PlotService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/plot")
public class PlotController {

    private static final Logger log = LoggerFactory.getLogger(PlotController.class);
    private final PlotService plotService;

    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @GetMapping
    public List<PlotDto> getAll() {
        log.info("Called get all plot controller");
        return this.plotService.getAll();
    }

    @GetMapping("/{id}")
    public PlotDto getById(@PathVariable Long id) {
        log.info("Called get by id plot controller");
        return this.plotService.getById(id);
    }

    @GetMapping("/planing")
    public List<PlotDto> getPlaningPlot() {
        log.info("Called get planing plot controller");
        return this.plotService.getPlaningPlot();
    }

    @PostMapping
    public PlotDto create(@RequestBody @Valid PlotDto plotToCreate) {
        log.info("Called create plot controller");
        return this.plotService.create(plotToCreate);
    }

    @PatchMapping("/{id}")
    public PlotDto update(@PathVariable Long id, @RequestBody @Valid PlotDto plotToUpdate) {
        log.info("Called update plot controller");
        return this.plotService.update(id, plotToUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Called delete plot controller");
        this.plotService.delete(id);
    }
}
