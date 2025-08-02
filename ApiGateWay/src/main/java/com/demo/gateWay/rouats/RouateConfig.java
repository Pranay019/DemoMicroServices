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
	        .route("LoginService", r -> r.path("/gateway/demo/**")
	            .filters(f -> f.rewritePath("/gateway/demo/(?<segment>.*)", "/demo/${segment}"))
	            .uri("lb://LOGINSERVICE"))
	        .route("profileService", r -> r.path("/gateway/profileService/**")
	            .filters(f -> f.rewritePath("/gateway/profileService/(?<segment>.*)", "/profileService/${segment}"))
	            .uri("lb://PROFILESERVICE"))
	        .build();
	}

}
