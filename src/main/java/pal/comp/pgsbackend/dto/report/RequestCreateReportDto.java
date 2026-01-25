package pal.comp.pgsbackend.dto.report;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestCreateReportDto(
    @NotNull
    Long planId,
    @NotNull
    Float fact,
    @NotNull
    LocalDate date,
    @NotNull
    String whoSend,
    String machine,
    String comment
) {
}
