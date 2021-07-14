package com.github.tgiachi.sofa.sofaserver.rest;

import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponse;
import com.github.tgiachi.sofa.sofaserver.services.MaintenanceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
@RequestMapping("/api/v1/maintenance")
public class MaintenanceRestController {

    private final Executor executor;
    private final MaintenanceService maintenanceService;

    public MaintenanceRestController(@Qualifier("taskExecutor") Executor executor, MaintenanceService maintenanceService) {
        this.executor = executor;
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/update_artists")
    public ResponseEntity<SofaRestResponse<Object>> updateAllArtists() {
        executor.execute(maintenanceService::updateAllArtists);
        return ResponseEntity.ok(SofaRestResponse.builder().data("ok").build());
    }
}
