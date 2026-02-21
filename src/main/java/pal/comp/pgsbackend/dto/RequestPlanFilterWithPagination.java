package pal.comp.pgsbackend.dto;

public record RequestPlanFilterWithPagination(
        Long plotId,
        Long typeWorkId,
        Long subtypeWorkId,
        String productionName,
        Boolean isActive,
        Integer page,
        Integer size
) {
}
