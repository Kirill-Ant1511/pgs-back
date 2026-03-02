package pal.comp.pgsbackend.dto.machine;

import jakarta.validation.constraints.NotNull;

public record RequestMachineDto(
        @NotNull
        String name
) {
}
