package com.mountana.api.data.repository;

import com.mountana.api.data.entity.MountainImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MountainImageRepository extends JpaRepository<MountainImage, UUID> {
    Iterable<MountainImage> findAllByIsDeleted(boolean isDeleted);
    Optional<MountainImage> findByIdAndIsDeleted(UUID id, boolean isDeleted);
    Iterable<MountainImage> findAllByMountainIdAndIsDeleted(UUID id, boolean isDeleted);
}
