package pal.comp.pgsbackend.dto.plan;

public record RequestAddMachineDto(
        Long planId,
        Long machineId
) {
}
