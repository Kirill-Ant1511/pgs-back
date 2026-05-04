package pal.comp.pgsbackend.repository;


import jakarta.transaction.Transactional;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pal.comp.pgsbackend.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByTelegramId(String telegramId);


    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.userPlots")
    List<UserEntity> findAll();


    @Modifying
    @Query(value = """
    INSERT INTO user_plots (user_id, plot_id) VALUES (:userId, :plotId)
""", nativeQuery = true)
    @Transactional
    void addPlotForUser(@Param("userId") Long userId, @Param("plotId") Long plotId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_plots WHERE user_id = :userId AND plot_id = :plotId",nativeQuery = true)
    void removePlotFromUser(@Param("userId") Long userId, @Param("plotId") Long plotId);
}
