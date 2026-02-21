package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.RequestPlanFilterWithPagination;
import pal.comp.pgsbackend.dto.plan.RequestCreatePlanDto;
import pal.comp.pgsbackend.dto.plan.RequestPlanFilter;
import pal.comp.pgsbackend.dto.plan.RequestUpdatePlanDto;
import pal.comp.pgsbackend.dto.plan.ResponsePlanDto;
import pal.comp.pgsbackend.mapper.PlanMapper;
import pal.comp.pgsbackend.repository.PlanRepository;

import java.util.List;

@Service
public class PlanService {
    private static final Logger log = LoggerFactory.getLogger(PlanService.class);
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    public PlanService(PlanRepository planRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
    }

    public List<ResponsePlanDto> getAll(RequestPlanFilter filter) {
        log.info("Get all plans by filter = {}", filter);
        var plansEntity = this.planRepository.findAllByFilter(
                filter.plotId(),
                filter.typeWorkId(),
                filter.subtypeWorkId(),
                filter.productionName(),
                filter.isActive()
        );
        return plansEntity.stream().map(planMapper::toDto).toList();
    }

    public List<ResponsePlanDto> getAllWithPagination(RequestPlanFilterWithPagination filter) {
        log.info("Get all plans by filter = {}", filter);
        var page = filter.page() != null ? filter.page() : 0;
        var size = filter.size() != null ? filter.size() : 10;
        var pageable = Pageable.ofSize(size).withPage(page);
        var plansEntity = this.planRepository.findAllByFilter(
                filter.plotId(),
                filter.typeWorkId(),
                filter.subtypeWorkId(),
                filter.productionName(),
                filter.isActive(),
                pageable
        );
        return plansEntity.stream().map(planMapper::toDto).toList();
    }

    public ResponsePlanDto findByForeignKey(
            Long plotId,
            Long typeWorkId,
            Long subtypeWorkId,
            String productionName,
            Boolean isActive
    ) {
        var planEntity = this.planRepository.findPlanEntitiesByPlotIdAndTypeWorkIdAndSubtypeWorkIdAndProductionNameAndIsActive(plotId, typeWorkId, subtypeWorkId, productionName, isActive);
        return this.planMapper.toDto(planEntity);
    }

    public ResponsePlanDto getById(Long id) {
        log.info("Get plan by id. Id = {}", id);
        var planEntity = this.planRepository.findById(id);
        if(planEntity.isEmpty())
            throw new EntityNotFoundException("Такого плана не существует. Id = " + id);
        return planMapper.toDto(planEntity.get());
    }

    public ResponsePlanDto create(RequestCreatePlanDto planToCreate) {
        log.info("Create new plan: {} {} {}", planToCreate.plotId(), planToCreate.typeWorkId(), planToCreate.subtypeWorkId());
        var planEntity = planMapper.toEntity(planToCreate);
        if (planEntity.getProductionName() == null)
            planEntity.setProductionName("");
        planEntity.setActive(true);
        var planEntitySaved = this.planRepository.save(planEntity);
        return planMapper.toDto(planEntitySaved);
    }

    public ResponsePlanDto update(Long id, RequestUpdatePlanDto planToUpdate) {
        log.info("Updating plan by id = {}; new data: {}", id, planToUpdate.toString());
        var planEntity = this.planRepository.findById(id);
        if (planEntity.isEmpty())
            throw new EntityNotFoundException("Такого плана не существует. Id = " + id);

        planEntity.get().getPlot().setId(
                planToUpdate.plotId() != null ? planToUpdate.plotId() : planEntity.get().getPlot().getId()
        );
        planEntity.get().getTypeWork().setId(
                planToUpdate.typeWorkId() != null ? planToUpdate.typeWorkId() : planEntity.get().getTypeWork().getId()
        );
        planEntity.get().getSubtypeWork().setId(
                planToUpdate.subtypeWorkId() != null ? planToUpdate.subtypeWorkId() : planEntity.get().getSubtypeWork().getId()
        );
        planEntity.get().setProductionName(
                planToUpdate.productionName() != null ? planToUpdate.productionName() : planEntity.get().getProductionName()
        );
        planEntity.get().setVolume(
                planToUpdate.volume() != null ? planToUpdate.volume() : planEntity.get().getVolume()
        );
        planEntity.get().setStartDate(
                planToUpdate.startDate() != null ? planToUpdate.startDate() : planEntity.get().getStartDate()
        );
        planEntity.get().setEndDate(
                planToUpdate.endDate() != null ? planToUpdate.endDate() : planEntity.get().getEndDate()
        );
        planEntity.get().setActive(
                planToUpdate.isActive() != null ? planToUpdate.isActive() : planEntity.get().getActive()
        );
        var planEntitySaved = this.planRepository.save(planEntity.get());
        return planMapper.toDto(planEntitySaved);
    }

    public void delete(Long id) {
        log.info("Deleting plan by id = {}", id);
        var plan = this.planRepository.findById(id);
        if (plan.isEmpty())
            throw new EntityNotFoundException("Такого плана не существует. Id = " + id);
        this.planRepository.deleteById(id);
    }

}
