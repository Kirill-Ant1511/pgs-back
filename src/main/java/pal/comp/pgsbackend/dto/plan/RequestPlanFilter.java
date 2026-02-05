package pal.comp.pgsbackend.dto.plan;

public record RequestPlanFilter(
        Long plotId,
        Long typeWorkId,
        Long subtypeWorkId,
        String productionName,
        Boolean isActive
) {
    @Override
    public String toString() {
        return "Filter: plotId = " + plotId + " typeWorkId " + typeWorkId + " subtypeWorkId " + subtypeWorkId + " productionName " + productionName;
    }
}
