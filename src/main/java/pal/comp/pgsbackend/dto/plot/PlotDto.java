package pal.comp.pgsbackend.dto.plot;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record PlotDto(
        @Null
        Long id,
        @NotNull
        String name
) {
        @Override
        public String toString() {
                return "Name = " + this.name;
        }
}
