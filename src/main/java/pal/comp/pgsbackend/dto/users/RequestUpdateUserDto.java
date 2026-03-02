package pal.comp.pgsbackend.dto.users;

import pal.comp.pgsbackend.entity.Role;

import java.util.List;

public record RequestUpdateUserDto(
        String name,
        String surname,
        String telegramId,
        Role role,
        List<Long> plotIds
) {
}
