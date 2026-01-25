package pal.comp.pgsbackend.dto.subtypework;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record SubtypeWorkDto(
        @Null
        Long id,
        @NotNull
        String code,
        @NotNull
        String name,
        @NotNull
        String unitMetering,
        @NotNull
        Long typeWorkId
) {
        @Override
        public String toString() {
                return "[Code: " + this.code + " Name: " + this.name + " Unit metering: " + unitMetering + "]";
        }
}
