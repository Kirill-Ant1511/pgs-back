package pal.comp.pgsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.MachineEntity;

import java.util.List;

public interface MachineRepository extends JpaRepository<MachineEntity, Long> {

    @Query("""
    SELECT m FROM MachineEntity m WHERE (:nameSubstring IS NULL OR CAST(m.name AS string) LIKE CONCAT('%', CAST(:nameSubstring AS string), '%'))
""")
    List<MachineEntity> findAllMachineByName(String nameSubstring);
}
