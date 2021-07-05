package com.github.tgiachi.sofa.sofaserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ScanService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Executor executor;

    public ScanService(Executor executor) {
        this.executor = executor;
    }


    public void scanDirectory(String directory) {
        executor.execute(() -> {
            try {
                var files = Files.find(Paths.get(directory),
                        Integer.MAX_VALUE,
                        (filePath, fileAttr) -> fileAttr.isRegularFile())
                       
                        .collect(Collectors.toList());

                logger.info("Found {} MP3", files.size());
            } catch (Exception ex) {
                logger.error("Error during scan", ex);
            }


        });
    }

}
