package pal.comp.pgsbackend.dto.plot;

import java.util.List;

public record RequestAddPlotForUserDto(
        Long userId,
        List<Long> plotId
) {
}
