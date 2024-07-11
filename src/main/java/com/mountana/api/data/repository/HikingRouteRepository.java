package com.mountana.api.data.repository;

import com.mountana.api.data.entity.HikingRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HikingRouteRepository extends JpaRepository<HikingRoute, UUID> {
    Iterable<HikingRoute> findAllByIsDeleted(boolean isDeleted);
    Optional<HikingRoute> findByIdAndIsDeleted(UUID id, boolean isDeleted);
}
