package org.example.garagemanagementgatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Value("${client-service-url:http://localhost:8081}")
    private String clientServiceUrl;

    @Value("${vehicule-service-url:http://localhost:8082}")
    private String vehiculeServiceUrl;

    @Value("${workshop-service-url:http://localhost:8083}")
    private String workshopServiceUrl;

    @Value("${invoice-service-url:http://localhost:8084}")
    private String invoiceServiceUrl;

    @Value("${notification-service-url:http://localhost:8085}")
    private String notificationServiceUrl;


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("client-route", r -> r.path("/api/v1/clients/**")
                        .filters(f -> f.addRequestHeader("X-Powered-By", "BTBG Gateway Service"))
                        .uri(clientServiceUrl))
                .route("vehicule-route", r -> r.path("/api/v1/vehicules/**")
                        .filters(f -> f.addRequestHeader("X-Powered-By", "BTBG Gateway Service"))
                        .uri(vehiculeServiceUrl))
                .route("workshop-route", r -> r.path("/api/v1/workshop/**")
                        .filters(f -> f.addRequestHeader("X-Powered-By", "BTBG Gateway Service"))
                        .uri(workshopServiceUrl))
                .route("invoice-route", r -> r.path("/api/v1/invoices/**")
                        .filters(f -> f.addRequestHeader("X-Powered-By", "BTBG Gateway Service"))
                        .uri(invoiceServiceUrl))
                .route("notification-route", r -> r.path("/api/v1/notification/**")
                        .filters(f -> f.addRequestHeader("X-Powered-By", "BTBG Gateway Service"))
                        .uri(notificationServiceUrl))
                .build();
    }
}

