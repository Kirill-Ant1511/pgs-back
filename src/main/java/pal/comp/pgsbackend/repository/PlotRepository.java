package pal.comp.pgsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pal.comp.pgsbackend.entity.PlotEntity;

import java.util.List;

public interface PlotRepository extends JpaRepository<PlotEntity, Long> {
    @Query("""
        SELECT DISTINCT pl from PlanEntity p left join PlotEntity pl ON pl.id = p.plot.id
    """)
    List<PlotEntity> findPlaningPlots();
}
