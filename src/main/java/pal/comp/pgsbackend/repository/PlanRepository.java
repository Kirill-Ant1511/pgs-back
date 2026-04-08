package pal.comp.pgsbackend.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.PlanEntity;

import java.util.List;

public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

    @Query("""
    SELECT p FROM PlanEntity p
    LEFT JOIN FETCH p.machines
    WHERE (:plotId IS NULL OR p.plot.id = :plotId)
    AND (:typeWorkId IS NULL OR p.typeWork.id = :typeWorkId)
    AND (:subtypeWorkId IS NULL OR p.subtypeWork.id = :subtypeWorkId)
    AND (:productionName IS NULL OR p.productionName = :productionName)
    AND (:isActive IS NULL OR p.isActive = :isActive)
    ORDER BY
        SUBSTRING(p.productionName FROM '^[A-Za-z]+') ASC,
        CAST(SUBSTRING(p.productionName FROM '[0-9]+$') AS INTEGER) ASC
""")
    List<PlanEntity> findAllByFilter(
            @Param("plotId") Long plotId,
            @Param("typeWorkId") Long typeWorkId,
            @Param("subtypeWorkId") Long subtypeWorkId,
            @Param("productionName") String productionName,
            @Param("isActive") Boolean isActive
    );


    @Query("""
    SELECT p FROM PlanEntity p
    WHERE (:plotId IS NULL OR p.plot.id = :plotId)
    AND (:typeWorkId IS NULL OR p.typeWork.id = :typeWorkId)
    AND (:subtypeWorkId IS NULL OR p.subtypeWork.id = :subtypeWorkId)
    AND (:productionName IS NULL OR CAST(p.productionName AS string) LIKE CONCAT('%', CAST(:productionName AS string), '%'))
    AND (:isActive IS NULL OR p.isActive = :isActive)
""")
    List<PlanEntity> findAllByFilter(
            @Param("plotId") Long plotId,
            @Param("typeWorkId") Long typeWorkId,
            @Param("subtypeWorkId") Long subtypeWorkId,
            @Param("productionName") String productionName,
            @Param("isActive") Boolean isActive,
            Pageable pageable
    );

    @Modifying
    @Query(value = """
    INSERT INTO machine_plans (machine_id, plan_id) VALUES (:machineId, :planId)
""", nativeQuery = true)
    @Transactional
    void addMachineInPlan(@Param("machineId") Long machineId, @Param("planId") Long planId);



    PlanEntity findPlanEntitiesByPlotIdAndTypeWorkIdAndSubtypeWorkIdAndProductionNameAndIsActive(
            Long plotId,
            Long typeWorkId,
            Long subtypeWorkId,
            String productionName,
            Boolean isActive
    );
}
