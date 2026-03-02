package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.RequestPlanFilterWithPagination;
import pal.comp.pgsbackend.dto.plan.*;
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
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        log.info("Called get all plan controller");
        if (page != null && size != null) {
            var filter = new RequestPlanFilterWithPagination(
                    plotId,
                    typeWorkId,
                    subtypeWorkId,
                    productionName,
                    isActive,
                    page,
                    size
            );
            return planService.getAllWithPagination(filter);
        }
        var filter = new RequestPlanFilter(
                plotId,
                typeWorkId,
                subtypeWorkId,
                productionName,
                isActive
        );
        return planService.getAll(filter);
    }

    @GetMapping("/by-fk")
    public ResponsePlanDto getByForeignKey(
            @RequestParam(name = "plotId") Long plotId,
            @RequestParam(name = "typeWorkId") Long typeWorkId,
            @RequestParam(name = "subtypeWorkId") Long subtypeWorkId,
            @RequestParam(name = "productionName") String productionName,
            @RequestParam(name = "isActive") Boolean isActive
    ) {
        log.info("Called get by foreign key {} {} {} {} {}",  plotId, typeWorkId, subtypeWorkId, productionName, isActive);
        return this.planService.findByForeignKey(plotId, typeWorkId, subtypeWorkId, productionName, isActive);
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

    @PostMapping("/add-machine")
    public ResponsePlanDto addMachine(@RequestBody RequestAddMachineDto dto) {
        log.info("Called add machine plan");
        return planService.addMachine(dto.planId(), dto.machineId());
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
