package pal.comp.pgsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pal.comp.pgsbackend.entity.MachineEntity;

public interface MachineRepository extends JpaRepository<MachineEntity, Long> {
}
