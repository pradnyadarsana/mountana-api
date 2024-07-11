package com.mountana.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MountainActivityStatusDTO {
    private int level;
    @NotBlank
    private String name;
    private String description;
    private String logoImgUrl;
}
