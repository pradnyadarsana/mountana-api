package com.mountana.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MountainDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String type;
    private int elevation;
    private double latitude;
    private double longitude;
    private UUID mountainActivityStatusId;
}
