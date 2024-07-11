package com.mountana.api.dto;

import jakarta.validation.constraints.NotBlank;
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
public class HikingRouteDTO {
    @NotNull
    private UUID mountainId;
    @NotBlank
    private String routeName;
    private int totalCheckpoint;
    private int avgHoursToSummit;
    private String waterSpringPoint;
    private String campsitePoint;
    private String peakName;
    @NotBlank
    private String startPointAddress;
    private double startPointLatitude;
    private double startPointLongitude;
    private BigDecimal ticketPrice;
    private String ticketUnit;
    private int hikingQuota;
    private String description;
}
