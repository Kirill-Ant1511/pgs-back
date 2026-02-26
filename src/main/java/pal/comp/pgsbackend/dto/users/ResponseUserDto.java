package pal.comp.pgsbackend.dto.users;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import pal.comp.pgsbackend.entity.PlotEntity;
import pal.comp.pgsbackend.entity.Role;

import java.util.List;

public record ResponseUserDto(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String surname,
        @NotNull
        String telegramId,
        @NotNull
        Role role,
        List<PlotEntity> plots
) {
    @Override
    public String toString() {
        return "Name: " + name + " Surname: " + surname + " telegramId " + telegramId;
    }
}
