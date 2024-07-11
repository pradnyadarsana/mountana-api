package com.mountana.api.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Mountain extends BaseEntity{
    @NotEmpty
    private String name;
    private String description;
    private String type;
    private int elevation;
    private double latitude;
    private double longitude;
    @Nullable
    private UUID mountainActivityStatusId;

    @JsonIgnoreProperties("mountains")
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="mountainActivityStatusId", insertable = false, updatable = false)
    private MountainActivityStatus mountainActivityStatus;

    @JsonIgnoreProperties("mountain")
    @OneToMany(mappedBy = "mountain", fetch = FetchType.LAZY, orphanRemoval=true, cascade={CascadeType.ALL})
    private List<HikingRoute> hikingRoutes;

    @JsonIgnoreProperties("mountain")
    @OneToMany(mappedBy = "mountain", fetch = FetchType.LAZY, orphanRemoval=true, cascade={CascadeType.ALL})
    private List<MountainImage> mountainImages;
}
