package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.machine.RequestMachineDto;
import pal.comp.pgsbackend.dto.machine.ResponseMachineDto;
import pal.comp.pgsbackend.entity.MachineEntity;

@Component
public class MachineMapper {
    public MachineEntity toEntity(RequestMachineDto dto) {
        return new MachineEntity(dto.name());
    }

    public ResponseMachineDto toDto(MachineEntity entity) {
        return new ResponseMachineDto(entity.getId(), entity.getName());
    }
}
