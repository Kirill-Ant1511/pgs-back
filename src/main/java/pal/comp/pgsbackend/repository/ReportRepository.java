package pal.comp.pgsbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.ReportEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    @Query("""
    SELECT r FROM ReportEntity r
    WHERE (:planId IS NULL OR r.plan.id = :planId)
    AND (:plotId IS NULL OR r.plan.plot.id = :plotId)
    AND (:typeWorkId IS NULL OR r.plan.typeWork.id = :typeWorkId)
    AND (:subtypeWorkId IS NULL OR r.plan.subtypeWork.id = :subtypeWorkId)
    AND (:productionName IS NULL OR r.plan.productionName = :productionName)
    AND (r.date >= COALESCE(:startDate, r.date))
    AND (r.date <= COALESCE(:endDate, r.date))
    AND (r.date = COALESCE(:constDate, r.date))
    """)
//    AND (r.date BETWEEN COALESCE(:startDate, r.date) AND COALESCE(:endDate, r.date))
    //AND (c.expiredTo <= COALESCE(:expiredTo, c.expiredTo))
    List<ReportEntity> getReportsByFilter(
            @Param("planId") Long planId,
            @Param("plotId") Long plotId,
            @Param("typeWorkId") Long typeWorkId,
            @Param("subtypeWorkId") Long subtypeWorkId,
            @Param("productionName") String productionName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("constDate") LocalDate constDate
    );

    @Query("""
    SELECT r FROM ReportEntity r
    WHERE (:planId IS NULL OR r.plan.id = :planId)
    AND (:plotId IS NULL OR r.plan.plot.id = :plotId)
    AND (:typeWorkId IS NULL OR r.plan.typeWork.id = :typeWorkId)
    AND (:subtypeWorkId IS NULL OR r.plan.subtypeWork.id = :subtypeWorkId)
    AND (:productionName IS NULL OR CAST(r.plan.productionName AS string) LIKE CONCAT('%', CAST(:productionName AS string), '%'))
    AND (r.date >= COALESCE(:startDate, r.date))
    AND (r.date <= COALESCE(:endDate, r.date))
    AND (r.date = COALESCE(:constDate, r.date))
""")
    List<ReportEntity> getReportsByFilterWithPagination(
            @Param("planId") Long planId,
            @Param("plotId") Long plotId,
            @Param("typeWorkId") Long typeWorkId,
            @Param("subtypeWorkId") Long subtypeWorkId,
            @Param("productionName") String productionName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("constDate") LocalDate constDate,
            Pageable pageable
    );

    @Modifying
    @Query("""
    UPDATE ReportEntity r1
    SET r1.delta = (
        SELECT round(r1.fact - COALESCE(r2.fact, 0), 2)
        FROM ReportEntity r2
        WHERE r2.date < r1.date and r2.plan.id = :planId
        ORDER BY r2.date DESC
        LIMIT 1
    ) where r1.plan.id = :planId and EXISTS (
        SELECT 1
        FROM ReportEntity r2
        WHERE r2.date < r1.date and r2.plan.id = :planId
    )
""")
    void resetDelta(
            @Param("planId") Long planId
    );
}
