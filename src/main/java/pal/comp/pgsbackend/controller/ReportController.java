package pal.comp.pgsbackend.controller;

import jakarta.validation.Valid;
import org.apache.catalina.filters.RequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.report.RequestByFilterDto;
import pal.comp.pgsbackend.dto.report.RequestCreateReportDto;
import pal.comp.pgsbackend.dto.report.ResponseReportDto;
import pal.comp.pgsbackend.services.ReportService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/report")
public class ReportController {
    private static final Logger log = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;


    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ResponseReportDto> getAll(
            @RequestParam(value = "planId", required = false) Long planId,
            @RequestParam(value = "plotId", required = false) Long PlotId,
            @RequestParam(value = "typeWorkId", required = false) Long typeWorkId,
            @RequestParam(value = "subtypeWorkId", required = false) Long subtypeWorkId,
            @RequestParam(value = "productionName", required = false) String productionName,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "constDate", required = false) LocalDate constDate,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
            ) {
        log.info("Called get all reports controller");
        if (page != null && size != null) {
            var filter = new RequestByFilterDto(
                    planId,
                    PlotId,
                    typeWorkId,
                    subtypeWorkId,
                    productionName,
                    startDate,
                    endDate,
                    constDate,
                    page,
                    size
            );
            return reportService.getAllByFilterWithPagination(filter);
        }
        var filter = new RequestByFilterDto(
                planId,
                PlotId,
                typeWorkId,
                subtypeWorkId,
                productionName,
                startDate,
                endDate,
                constDate,
                null,
                null
        );
        return reportService.getAllByFilter(filter);
    }

    @PostMapping
    public ResponseReportDto create(@RequestBody @Valid RequestCreateReportDto reportToCreate) {
        log.info("Called create report controller");
        return reportService.create(reportToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Called delete report controller");
        reportService.delete(id);
    }
}
