package pal.comp.pgsbackend.dto.users;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import pal.comp.pgsbackend.entity.Role;

import java.util.List;

public record RequestUserDto(
        @NotNull
        String name,
        @NotNull
        String surname,
        @NotNull
        String telegramId,
        @NotNull
        Role role,
        List<Long> plotIds
) {
}
