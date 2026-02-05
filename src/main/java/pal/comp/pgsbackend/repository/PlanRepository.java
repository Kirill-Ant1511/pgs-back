package pal.comp.pgsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.PlanEntity;

import java.util.List;

public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

    @Query("""
    SELECT p FROM PlanEntity p
    WHERE (:plotId IS NULL OR p.plot.id = :plotId)
    AND (:typeWorkId IS NULL OR p.typeWork.id = :typeWorkId)
    AND (:subtypeWorkId IS NULL OR p.subtypeWork.id = :subtypeWorkId)
    AND (:productionName IS NULL OR p.productionName = :productionName)
    AND (:isActive IS NULL OR p.isActive = :isActive)
""")
    List<PlanEntity> findAllByFilter(
            @Param("plotId") Long plotId,
            @Param("typeWorkId") Long typeWorkId,
            @Param("subtypeWorkId") Long subtypeWorkId,
            @Param("productionName") String productionName,
            @Param("isActive") Boolean isActive
    );
}
