package pal.comp.pgsbackend.dto.plot;

public record RequestAddPlotForUserDto(
        Long userId,
        Long plotId
) {
}
