package pal.comp.pgsbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.TypeWorkEntity;

import java.util.List;

public interface TypeWorkRepository extends JpaRepository<TypeWorkEntity, Long> {
    TypeWorkEntity findByName(String name);

    @Query("""
        select DISTINCT tw from PlanEntity p
        left join TypeWorkEntity tw ON tw.id = p.typeWork.id where p.plot.id = :plotId and p.isActive = true
    """)
    List<TypeWorkEntity> findPlaningTypeWork(
            @Param("plotId") Long plotId
    );



    @Query("""
        SELECT tw FROM TypeWorkEntity tw
        WHERE (:nameSubstring IS NULL OR tw.name LIKE LOWER(CONCAT('%', :nameSubstring, '%')))
    """)
    List<TypeWorkEntity> findTypeWorkByNameSubstring(
            @Param("nameSubstring") String nameSubstring,
            Pageable pageable
    );
}
