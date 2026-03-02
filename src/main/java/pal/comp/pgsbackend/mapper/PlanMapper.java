package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.plan.RequestCreatePlanDto;
import pal.comp.pgsbackend.dto.plan.ResponsePlanDto;
import pal.comp.pgsbackend.entity.PlanEntity;

@Component
public class PlanMapper {
    private final PlotMapper plotMapper;
    private final TypeWorkMapper typeWorkMapper;
    private final SubtypeWorkMapper subtypeWorkMapper;
    private final MachineMapper machineMapper;

    public PlanMapper(PlotMapper plotMapper, TypeWorkMapper typeWorkMapper, SubtypeWorkMapper subtypeWorkMapper, MachineMapper machineMapper) {
        this.plotMapper = plotMapper;
        this.typeWorkMapper = typeWorkMapper;
        this.subtypeWorkMapper = subtypeWorkMapper;
        this.machineMapper = machineMapper;
    }

    public ResponsePlanDto toDto(PlanEntity entity) {
         return new ResponsePlanDto(
                 entity.getId(),
                 plotMapper.toDto(entity.getPlot()),
                 typeWorkMapper.toDto(entity.getTypeWork()),
                 subtypeWorkMapper.toDto(entity.getSubtypeWork()),
                 entity.getProductionName(),
                 entity.getVolume(),
                 entity.getStartDate(),
                 entity.getEndDate(),
                 entity.getActive(),
                 entity.getMachines() == null ?
                    null :
                    entity.getMachines().stream().map(machineMapper::toDto).toList()
         );
    }

    public PlanEntity toEntity(RequestCreatePlanDto dto) {
        return new PlanEntity(
                dto.id(),
                dto.plotId(),
                dto.typeWorkId(),
                dto.subtypeWorkId(),
                dto.productionName(),
                dto.volume(),
                dto.startDate(),
                dto.endDate(),
                dto.isActive()
        );
    }
}
