package com.mountana.api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("external-services")
@AllArgsConstructor
@Getter
public class ExternalServicesProperties {
    private final String imageAssetsUrl;
}
