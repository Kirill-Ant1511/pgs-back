package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.subtypework.SubtypeWorkDto;
import pal.comp.pgsbackend.entity.SubtypeWorkEntity;

@Component
public class SubtypeWorkMapper {
    public SubtypeWorkDto toDto(SubtypeWorkEntity entity) {
        return new SubtypeWorkDto(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getUnitMetring(),
                entity.getTypeWorkId()
        );
    }

    public SubtypeWorkEntity toEntity(SubtypeWorkDto dto) {
        return new SubtypeWorkEntity(
                dto.id(),
                dto.code(),
                dto.name(),
                dto.typeWorkId(),
                dto.unitMetering()
        );
    }
}
