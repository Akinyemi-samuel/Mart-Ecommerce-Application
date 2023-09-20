package com.samfrosh.repository;

import com.samfrosh.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM notifications n WHERE n.user_id = ?1 OR n.user_id = ''"
    )
    Optional<List<Notification>> findByIdAndByAll(String id);
}
