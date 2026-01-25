package pal.comp.pgsbackend.dto.typework;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record TypeWorkDto(
        @Null
        Long id,
        @NotNull
        String code,
        @NotNull
        String name
) {
        @Override
        public String toString() {
                return "Code: " + this.code + " Name: " + this.name;
        }
}
