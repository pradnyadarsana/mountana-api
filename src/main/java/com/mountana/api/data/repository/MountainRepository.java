package com.mountana.api.data.repository;

import com.mountana.api.data.entity.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, UUID> {
    Iterable<Mountain> findAllByIsDeleted(boolean isDeleted);
    Optional<Mountain> findByIdAndIsDeleted(UUID id, boolean isDeleted);
}
