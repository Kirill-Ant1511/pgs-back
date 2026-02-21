package pal.comp.pgsbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.SubtypeWorkEntity;
import pal.comp.pgsbackend.entity.TypeWorkEntity;

import java.util.List;

public interface SubtypeWorkRepository extends JpaRepository<SubtypeWorkEntity, Long> {




    @Query("""
    SELECT sw FROM SubtypeWorkEntity sw
    WHERE (:name IS NULL OR CAST(sw.name AS string) LIKE CONCAT('%', CAST(:name AS string), '%'))
    AND (:code IS NULL OR CAST(sw.code AS string) LIKE CONCAT('%', CAST(:code AS string), '%'))
    AND (:typeWorkId IS NULL OR sw.typeWorkId = :typeWorkId)
""")
    List<SubtypeWorkEntity> findAll(
            @Param("code") String code,
            @Param("name") String name,
            @Param("typeWorkId") Long typeWorkId,
            Pageable pageable
    );





    List<SubtypeWorkEntity> findByTypeWorkId(Long typeWorkId);
    SubtypeWorkEntity findByName(String name);

    @Query("""
        select DISTINCT sw from PlanEntity p
        left join SubtypeWorkEntity sw ON sw.id = p.subtypeWork.id where p.plot.id = :plotId and p.typeWork.id = :typeWorkId and p.isActive = true
    """)
    List<SubtypeWorkEntity> findPlaningSubtypeWork(
            @Param("plotId") Long plotId,
            @Param("typeWorkId") Long typeWorkId
    );



    @Query("""
        SELECT sw FROM SubtypeWorkEntity sw
        WHERE (:typeWorkId IS NULL OR sw.typeWorkId = :typeWorkId)
        AND (:nameSubstring IS NULL OR sw.name LIKE LOWER(CONCAT('%', :nameSubstring, '%')))
    """)
    List<SubtypeWorkEntity> findSubtypeWorkByFilters(
            @Param("typeWorkId") Long typeWorkId,
            @Param("nameSubstring") String nameSubstring,
            Pageable pageable
    );
}
