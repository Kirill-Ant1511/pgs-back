package pal.comp.pgsbackend.dto.report;

import java.time.LocalDate;
import java.util.Date;

public record RequestByFilterDto(
        Long planId,
        Long plotId,
        Long typeWorkId,
        Long subtypeWorkId,
        String productionName,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate constDate
) {
    @Override
    public String toString() {
        return "Filter value: planId = " + planId
                + " plotId = " + plotId
                + " typeWorkId = " + typeWorkId
                + " subtypeWorkId = " + subtypeWorkId
                + " productionName = " + productionName
                + " startDate = " + startDate
                + " endDate = " + endDate
                + " constDate = " + constDate;
    }
}
