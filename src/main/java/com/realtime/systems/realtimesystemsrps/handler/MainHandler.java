package com.realtime.systems.realtimesystemsrps.handler;

import com.realtime.systems.realtimesystemsrps.service.ProcessingService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MainHandler {


    private ProcessingService processingService;

    public MainHandler(ProcessingService processingService) {
        this.processingService = processingService;
    }

    public Mono<ServerResponse> getDispersion(ServerRequest request) {
        String date = request.pathVariable("since");

        return processingService.computeDispersion(date);
    }

    public Mono<ServerResponse> getRate(ServerRequest request) {
        String date = request.pathVariable("date");

        return processingService.getRate(date);
    }
}
