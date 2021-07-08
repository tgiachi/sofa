package com.github.tgiachi.sofa.sofaserver.rest;

import com.github.tgiachi.sofa.sofaserver.services.StreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/")
public class StreamRestController {

    private final StreamService streamService;

    public StreamRestController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/stream/{hash}")
    public Mono<ResponseEntity<byte[]>> streamVideo(@RequestHeader(value = "Range", required = false) String httpRangeList,
                                                    @PathVariable("hash") String hash) {
        return Mono.just(streamService.prepareContent(hash, httpRangeList));
    }
}
