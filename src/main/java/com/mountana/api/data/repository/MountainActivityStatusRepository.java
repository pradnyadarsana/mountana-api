package com.mountana.api.data.repository;

import com.mountana.api.data.entity.MountainActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MountainActivityStatusRepository extends JpaRepository<MountainActivityStatus, UUID> {
    Iterable<MountainActivityStatus> findAllByIsDeleted(boolean isDeleted);
    Optional<MountainActivityStatus> findByIdAndIsDeleted(UUID id, boolean isDeleted);
}
