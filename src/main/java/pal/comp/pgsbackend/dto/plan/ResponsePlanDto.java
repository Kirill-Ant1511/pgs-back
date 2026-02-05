package pal.comp.pgsbackend.dto.plan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import pal.comp.pgsbackend.dto.plot.PlotDto;
import pal.comp.pgsbackend.dto.subtypework.SubtypeWorkDto;
import pal.comp.pgsbackend.dto.typework.TypeWorkDto;

import java.time.LocalDate;

public record ResponsePlanDto(
        Long id,
        PlotDto plot,
        TypeWorkDto typeWork,
        SubtypeWorkDto subtypeWork,
        String productionName,
        Float volume,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isActive
) {
}
