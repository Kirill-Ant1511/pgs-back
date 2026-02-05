package pal.comp.pgsbackend.dto.plan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;

public record RequestUpdatePlanDto(
        Long plotId,
        Long typeWorkId,
        Long subtypeWorkId,
        String productionName,
        Float volume,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isActive
) {
    @Override
    public String toString() {
        return "PlotId: " + plotId + " typeWorkId " + typeWorkId + " subtypeWorkId " + subtypeWorkId + " productionName " + productionName + " volume " + volume + " startDate " + startDate + " endDate " + endDate;
    }
}
