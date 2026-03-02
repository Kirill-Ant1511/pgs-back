package pal.comp.pgsbackend.dto.plan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;
import java.util.List;

public record RequestCreatePlanDto(
        @Null
        Long id,
        @NotNull
        Long plotId,
        @NotNull
        Long typeWorkId,
        @NotNull
        Long subtypeWorkId,
        String productionName,
        @NotNull
        Float volume,
        LocalDate startDate,
        LocalDate endDate,
        @Null
        Boolean isActive,
        List<Long> machineIds
) {
}
