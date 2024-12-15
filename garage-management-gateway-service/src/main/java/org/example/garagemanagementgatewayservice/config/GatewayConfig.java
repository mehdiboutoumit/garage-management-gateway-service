package org.example.garagemanagementgatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Workshop Service Route
                .route("workshop-route", r -> r.path("/api/v1/workshop/**")
                        .filters(f -> f.addRequestHeader("X-Powered-By", "BTBG Gateway Service"))
                        .uri("http://localhost:8083")) // Update to match your service URL
                // Notification Service Route
                .route("notification-route", r -> r.path("/api/v1/notification/**")
                        .filters(f -> f.addRequestHeader("X-Powered-By", "BTBG Gateway Service"))
                        .uri("http://localhost:8084")) // Update to match your service URL
                .build();
    }
}

