package com.github.tgiachi.sofa.sofaserver.rest;

import com.github.tgiachi.sofa.sofaserver.dto.AutocompleteResultDto;
import com.github.tgiachi.sofa.sofaserver.mappers.AutocompleteResultMapper;
import com.github.tgiachi.sofa.sofaserver.services.ScanService;
import com.github.tgiachi.sofa.sofaserver.services.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final ScanService scanService;
    private final SearchService searchService;
    private final AutocompleteResultMapper autocompleteResultMapper;

    public SearchController(ScanService scanService, SearchService searchService, AutocompleteResultMapper autocompleteResultMapper) {
        this.scanService = scanService;
        this.searchService = searchService;
        this.autocompleteResultMapper = autocompleteResultMapper;
    }

    @GetMapping("/search/autocomplete")
    public DeferredResult<ResponseEntity<AutocompleteResultDto>> autocomplete(@RequestParam String text) {

        var out = new DeferredResult<ResponseEntity<AutocompleteResultDto>>();
        ForkJoinPool.commonPool().submit(() -> {
            out.setResult(ResponseEntity.ok(autocompleteResultMapper.toDto(searchService.autocomplete(text))));
        });

        return out;
    }

}
