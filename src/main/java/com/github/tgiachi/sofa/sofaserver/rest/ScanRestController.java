package com.github.tgiachi.sofa.sofaserver.rest;

import com.github.tgiachi.sofa.sofaserver.services.ScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scan")
public class ScanRestController {

    private final ScanService scanService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ScanRestController(ScanService scanService) {
        this.scanService = scanService;
    }

    @GetMapping("/directory")
    public ResponseEntity<String> scanDirectory(@RequestParam String directory) {

        logger.info("Request scanning directory: {}", directory);

        scanService.scanDirectory(directory);

        return ResponseEntity.ok(directory);
    }
}
