package com.tv.bang.api.gateway.config;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

public class GatewaySwaggerResourcesProvider implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    public GatewaySwaggerResourcesProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        resources.add(swaggerResource("default", "/v2/api-docs"));

        routeLocator.getRoutes().forEach(route -> {
            resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
