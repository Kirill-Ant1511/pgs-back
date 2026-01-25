package pal.comp.pgsbackend.dto.subtypework;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record UpdateSubtypeWorkDto(
        String code,
        String name,
        String unitMetering
) {
    @Override
    public String toString() {
        return "[Code: " + this.code + " Name: " + this.name + " Unit metering: " + unitMetering + "]";
    }
}