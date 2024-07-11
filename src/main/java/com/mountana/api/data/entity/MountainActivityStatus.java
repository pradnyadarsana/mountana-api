package com.mountana.api.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MountainActivityStatus extends BaseEntity{
    private int level;
    private String name;
    private String description;
    private String logoImgUrl;

    @JsonIgnoreProperties("mountainActivityStatus")
    @OneToMany(mappedBy = "mountainActivityStatus", fetch = FetchType.LAZY, orphanRemoval=true, cascade={CascadeType.ALL})
    private List<Mountain> mountains;
}
