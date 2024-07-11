package com.mountana.api.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MountainImage extends BaseEntity {
    @NotNull
    private UUID mountainId;
    @NotEmpty
    private String imageUrl;

    @JsonIgnoreProperties("mountainImages")
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="mountainId", insertable = false, updatable = false)
    private Mountain mountain;
}
