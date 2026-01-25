package pal.comp.pgsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pal.comp.pgsbackend.entity.ProjectManagerEntity;

import java.util.Optional;

public interface ProjectManagerRepository extends JpaRepository<ProjectManagerEntity, Long> {
    Optional<ProjectManagerEntity> findByTelegramId(String telegramId);
}
