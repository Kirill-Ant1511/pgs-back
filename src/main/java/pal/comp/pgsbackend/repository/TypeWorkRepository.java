package pal.comp.pgsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.TypeWorkEntity;

import java.util.List;

public interface TypeWorkRepository extends JpaRepository<TypeWorkEntity, Long> {
    TypeWorkEntity findByName(String name);

    @Query("""
        select DISTINCT tw from PlanEntity p
        left join TypeWorkEntity tw ON tw.id = p.typeWork.id where p.plot.id = :plotId
    """)
    List<TypeWorkEntity> findPlaningTypeWork(
            @Param("plotId") Long plotId
    );
}
