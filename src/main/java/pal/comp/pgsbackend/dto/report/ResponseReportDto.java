package pal.comp.pgsbackend.dto.report;

import pal.comp.pgsbackend.dto.plan.ResponsePlanDto;
import pal.comp.pgsbackend.entity.PlanEntity;

import java.time.LocalDate;

public record ResponseReportDto(
        Long id,
        PlanEntity plan,
        Float fact,
        Float delta,
        LocalDate date,
        String whoSend,
        String machine,
        String comment
) {
}
