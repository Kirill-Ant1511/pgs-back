package pal.comp.pgsbackend.repository;


import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pal.comp.pgsbackend.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByTelegramId(String telegramId);


    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.userPlots")
    List<UserEntity> findAll();
}
