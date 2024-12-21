package org.example.garagemanagementgatewayservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@RefreshScope
public class GatewayConfig {
    @Value("${client-service-url}")
    private String clientServiceUrl;

    @Value("${vehicule-service-url}")
    private String vehiculeServiceUrl;

    @Value("${workshop-service-url}")
    private String workshopServiceUrl;

    @Value("${invoice-service-url}")
    private String invoiceServiceUrl;

    @Value("${notification-service-url}")
    private String notificationServiceUrl;



    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {


        return builder.routes()
                .route("client-service-route", r -> r.path("/api/v1/clients/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("clientCircuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri(clientServiceUrl))
                .route("vehicule-service-route", r -> r.path("/api/v1/vehicules/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("vehiculeCircuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri(vehiculeServiceUrl))
                .route("workshop-service-route", r -> r.path("/api/v1/workshop/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("workshopCircuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri(workshopServiceUrl))
                .route("invoice-service-route", r -> r.path("/api/v1/invoices/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("invoiceCircuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri(invoiceServiceUrl))
                .route("notification-service-route", r -> r.path("/api/v1/notification/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("notificationCircuitBreaker")
                                .setFallbackUri("forward:/fallback")))
                        .uri(notificationServiceUrl))
                .build();
    }
}
