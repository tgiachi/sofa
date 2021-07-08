package com.github.tgiachi.sofa.sofaserver.rest;


import com.github.tgiachi.sofa.sofaserver.dto.StatisticsDto;
import com.github.tgiachi.sofa.sofaserver.services.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsRestController {

    private final StatisticsService statisticsService;

    public StatisticsRestController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public Mono<ResponseEntity<StatisticsDto>> getStats() {
        return Mono.just(ResponseEntity.ok(statisticsService.calculateStats()));
    }
}
