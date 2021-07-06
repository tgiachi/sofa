package com.github.tgiachi.sofa.sofaserver.rest;

import com.github.tgiachi.sofa.sofaserver.services.ScanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
@RequestMapping("/api/v1")
public class AdminRestController {

    private final ScanService scanService;

    public AdminRestController(ScanService scanService) {
        this.scanService = scanService;
    }

    @GetMapping("/scan")
    public ResponseEntity<String> performScan() {
        scanService.scanDirectory("/Users/squid/mp3");
        return ResponseEntity.ok("");
    }

}
