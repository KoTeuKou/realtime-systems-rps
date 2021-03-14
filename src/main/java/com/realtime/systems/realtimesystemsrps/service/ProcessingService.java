package com.realtime.systems.realtimesystemsrps.service;

import com.realtime.systems.realtimesystemsrps.domain.RatesForDate;
import com.realtime.systems.realtimesystemsrps.domain.RatesForPeriod;
import com.realtime.systems.realtimesystemsrps.util.Util;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessingService {

    private WebClient webClient;
    private String url = "https://api.exchangeratesapi.io/";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public ProcessingService() {
    }

    public Mono<ServerResponse> computeDispersion(String since) {
        webClient = buildWebClient();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("history")
                        .queryParam("start_at", since)
                        .queryParam("end_at", dateFormatter.format(Date.from(Instant.now())))
                        .queryParam("symbols", "USD")
                        .build())
                .retrieve()
                .bodyToMono(RatesForPeriod.class)
                .flatMap(body -> {
                    List<Double> processed = body.getRates()
                            .parallelStream()
                            .map(elem -> Math.pow(Math.tan(elem.getUsdValue()), 7.5))
                            .collect(Collectors.toList());
                    Double variance = Util.getVariance(processed);
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just(variance), Double.class);
                })
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getRate(String date) {
        webClient = buildWebClient();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(date)
                        .queryParam("symbols", "USD")
                        .build())
                .retrieve()
                .bodyToMono(RatesForDate.class)
                .flatMap(body -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(body.getUsdRate().getUsdValue()), Double.class))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public WebClient buildWebClient() {
        return WebClient.builder().baseUrl(url).exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build())
                .build();
    }

}
