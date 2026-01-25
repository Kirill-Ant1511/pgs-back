package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.plot.PlotDto;
import pal.comp.pgsbackend.entity.PlotEntity;

@Component
public class PlotMapper {

    public PlotDto toDto(PlotEntity entity) {
        return new PlotDto(entity.getId(), entity.getName());
    }

    public PlotEntity toEntity(PlotDto dto) {
        return new PlotEntity(dto.id(), dto.name());
    }
}
