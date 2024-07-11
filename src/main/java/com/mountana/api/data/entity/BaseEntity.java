package com.mountana.api.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdDate;
    @Column(updatable = false)
    private String createdBy;
    @UpdateTimestamp
    private Instant updatedDate;
    private String updatedBy;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}
