package com.realtime.systems.realtimesystemsrps.config;

import com.realtime.systems.realtimesystemsrps.handler.MainHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class MainRouter {

    @Bean
    public RouterFunction<ServerResponse> route(MainHandler mainHandler) {
        RequestPredicate dispersionRoute = RequestPredicates
                .GET("/dispersion/{since}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        RequestPredicate rateRoute = RequestPredicates
                .GET("/rate/{date}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions
                .route(dispersionRoute, mainHandler::getDispersion)
                .andRoute(rateRoute, mainHandler::getRate);
    }
}
