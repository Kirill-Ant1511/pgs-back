package pal.comp.pgsbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.PlotEntity;

import java.util.List;

public interface PlotRepository extends JpaRepository<PlotEntity, Long> {


    @Query("""
    SELECT p FROM PlotEntity p WHERE (:nameSubstring IS NULL OR CAST(p.name AS string) LIKE CONCAT('%', CAST(:nameSubstring AS string), '%'))
""")
    List<PlotEntity> findAll(@Param("nameSubstring") String nameSubstring);


    @Query("""
        SELECT DISTINCT pl from PlanEntity p left join PlotEntity pl ON pl.id = p.plot.id and p.isActive = true
    """)
    List<PlotEntity> findPlaningPlots();


    @Query("""
        SELECT p FROM PlotEntity p WHERE (:nameSubsrting IS NULL OR p.name LIKE LOWER(CONCAT('%', :nameSubstring, '%')))
    """)
    List<PlotEntity> findPlotsByNameSubstring(@Param("nameSubstring") String nameSubstring);
}
