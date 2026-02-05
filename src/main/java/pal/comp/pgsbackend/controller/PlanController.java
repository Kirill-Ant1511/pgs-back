package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.plan.RequestCreatePlanDto;
import pal.comp.pgsbackend.dto.plan.RequestPlanFilter;
import pal.comp.pgsbackend.dto.plan.RequestUpdatePlanDto;
import pal.comp.pgsbackend.dto.plan.ResponsePlanDto;
import pal.comp.pgsbackend.services.PlanService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/plan")
public class PlanController {
    private static final Logger log = LoggerFactory.getLogger(PlanController.class);
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<ResponsePlanDto> getAll(
            @RequestParam(value = "plotId", required = false) Long plotId,
            @RequestParam(value = "typeWorkId", required = false) Long typeWorkId,
            @RequestParam(value = "subtypeWorkId", required = false) Long subtypeWorkId,
            @RequestParam(value = "productionName", required = false) String productionName,
            @RequestParam(value = "isActive", required = false) Boolean isActive
    ) {
        log.info("Called get all plan controller");
        var filter = new RequestPlanFilter(
                plotId,
                typeWorkId,
                subtypeWorkId,
                productionName,
                isActive
        );
        return planService.getAll(filter);
    }

    @GetMapping("/{id}")
    public ResponsePlanDto getById(@PathVariable Long id) {
        log.info("Called get by id plan");
        return planService.getById(id);
    }

    @PostMapping
    public ResponsePlanDto create(@RequestBody @Valid RequestCreatePlanDto planToCreate) {
        log.info("Called create plan");
        return planService.create(planToCreate);
    }

    @PatchMapping("/{id}")
    public ResponsePlanDto update(@PathVariable Long id, @RequestBody @Valid RequestUpdatePlanDto planToUpdate) {
        log.info("Called update plan");
        return planService.update(id, planToUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Called delete plan");
        planService.delete(id);
    }
}
