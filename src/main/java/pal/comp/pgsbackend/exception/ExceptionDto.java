package pal.comp.pgsbackend.exception;

import java.time.LocalDateTime;

public record ExceptionDto(
        String message,
        String detailMessage,
        LocalDateTime exceptionTime
) {
}
