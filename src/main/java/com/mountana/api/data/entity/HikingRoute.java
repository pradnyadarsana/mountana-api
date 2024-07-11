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

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class HikingRoute extends BaseEntity {
    @NotNull
    private UUID mountainId;
    @NotEmpty
    private String routeName;
    private int totalCheckpoint;
    private int avgHoursToSummit;
    private String waterSpringPoint;
    private String campsitePoint;
    private String peakName;
    @NotEmpty
    private String startPointAddress;
    private double startPointLatitude;
    private double startPointLongitude;
    private BigDecimal ticketPrice;
    private String ticketUnit;
    private int hikingQuota;
    private String description;

    @JsonIgnoreProperties("hikingRoutes")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="mountainId", insertable = false, updatable = false)
    private Mountain mountain;
}
