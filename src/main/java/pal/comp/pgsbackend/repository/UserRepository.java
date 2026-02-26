package pal.comp.pgsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pal.comp.pgsbackend.entity.Users;

import java.util.Optional;

public interface ProjectManagerRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByTelegramId(String telegramId);
}
