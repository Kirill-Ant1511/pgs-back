package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.typework.TypeWorkDto;
import pal.comp.pgsbackend.entity.TypeWorkEntity;

@Component
public class TypeWorkMapper {
    public TypeWorkDto toDto(TypeWorkEntity entity) {
        return new TypeWorkDto(entity.getId(), entity.getCode(), entity.getName());
    }

    public TypeWorkEntity toEntity(TypeWorkDto dto) {
        return new TypeWorkEntity(dto.id(), dto.code(), dto.name());
    }
}
