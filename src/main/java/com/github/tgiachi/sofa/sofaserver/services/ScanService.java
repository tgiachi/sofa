package com.github.tgiachi.sofa.sofaserver.services;

import com.github.tgiachi.sofa.sofaserver.annotations.FileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.dao.UnTrackedDao;
import com.github.tgiachi.sofa.sofaserver.entities.UnTrackedEntity;
import com.github.tgiachi.sofa.sofaserver.exceptions.UnTrackedException;
import com.github.tgiachi.sofa.sofaserver.interfaces.IFileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class ScanService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final HashMap<String, Class<?>> handlers = new HashMap<>();

    private final Executor executor;
    private final ApplicationContext applicationContext;
    private UnTrackedDao unTrackedDao;

    public ScanService(Executor taskExecutor, ApplicationContext applicationContext, UnTrackedDao unTrackedDao) {
        this.executor = taskExecutor;
        this.applicationContext = applicationContext;
        this.unTrackedDao = unTrackedDao;
    }


    @PostConstruct
    public void init() {
        ReflectionUtils.getAnnotation(FileTypeHandler.class).forEach(a -> {
            var annotation = a.getAnnotation(FileTypeHandler.class);
            logger.info("Adding handler for {} ({})", annotation.extension(), a.getSimpleName());
            handlers.put(annotation.extension(), a);
        });
    }

    public void getFileInfo(Path path) {
        var handler = getHandlerForFile(path);

        if (handler == null) {
            logger.warn("Handler for file {} not found", path.getFileName());
        } else {
            executor.execute(() -> {
                try {
                    handler.processFile(path);

                } catch (UnTrackedException unTrackedException) {

                    logger.warn("Untracked entity {}", path.getFileName().toString());
                    var entity = new UnTrackedEntity();
                    entity.setFilename(path.toString());
                    entity.setFileSize(path.toFile().length());
                    entity.setFileHandler(handler.getClass().toString());
                    unTrackedDao.insert(entity);

                } catch (Exception ex) {
                    logger.error("Error during process file {} with handler: {}", path.getFileName(), handler.getClass().getSimpleName(), ex);
                }
            });
        }

    }

    private IFileTypeHandler getHandlerForFile(Path file) {
        for (var kv : handlers.entrySet()) {
            if (file.getFileName().toString().toLowerCase().endsWith(kv.getKey()))
                return (IFileTypeHandler) applicationContext.getBean(kv.getValue());
        }
        return null;
    }

    public void scanDirectory(String directory) {
        executor.execute(() -> {
            try {
                var files = Files.find(Paths.get(directory),
                        Integer.MAX_VALUE,
                        (filePath, fileAttr) -> fileAttr.isRegularFile())
                        .collect(Collectors.toList());

                logger.info("Found {} files", files.size());

                files.forEach(this::getFileInfo);


            } catch (Exception ex) {
                logger.error("Error during scan", ex);
            }


        });
    }

}
