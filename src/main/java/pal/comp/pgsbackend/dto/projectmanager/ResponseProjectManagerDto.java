package pal.comp.pgsbackend.dto.projectmanager;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record ResponseProjectManagerDto(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String surname,
        @NotNull
        String telegramId
) {
    @Override
    public String toString() {
        return "Name: " + name + " Surname: " + surname + " telegramId " + telegramId;
    }
}
