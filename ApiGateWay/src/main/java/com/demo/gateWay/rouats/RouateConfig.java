package com.demo.gateWay.rouats;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RouateConfig { 
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("LoginService", r -> r.path("/demo/**")
                .uri("lb://LOGINSERVICE"))
            .route("profileService", r -> r.path("/profileService/**")
                .uri("lb://PROFILESERVICE"))
            .build();
    }
}
