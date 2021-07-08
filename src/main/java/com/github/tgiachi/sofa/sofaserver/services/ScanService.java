package com.github.tgiachi.sofa.sofaserver.services;

import com.github.tgiachi.sofa.sofaserver.annotations.FileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.dao.UnTrackedDao;
import com.github.tgiachi.sofa.sofaserver.entities.ExceptionFileEntity;
import com.github.tgiachi.sofa.sofaserver.entities.UnTrackedEntity;
import com.github.tgiachi.sofa.sofaserver.exceptions.UnTrackedException;
import com.github.tgiachi.sofa.sofaserver.interfaces.handlers.IFileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.repository.ExceptionFileRepository;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import com.github.tgiachi.sofa.sofaserver.utils.ReflectionUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
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
    private final UnTrackedDao unTrackedDao;
    private final ExceptionFileRepository exceptionFileRepository;

    public ScanService(@Qualifier("taskExecutor") Executor taskExecutor, ApplicationContext applicationContext, UnTrackedDao unTrackedDao, ExceptionFileRepository exceptionFileRepository) {
        this.executor = taskExecutor;
        this.applicationContext = applicationContext;
        this.unTrackedDao = unTrackedDao;
        this.exceptionFileRepository = exceptionFileRepository;
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
                    var md5 = Md5Utils.md5File(path);
                    if (!handler.fileExists(md5, false)) {
                        handler.processFile(path);
                    } else {
                        logger.info("File {} already indexed", path.getFileName());
                    }
                } catch (UnTrackedException unTrackedException) {

                    logger.warn("Untracked entity {}", path.getFileName().toString());
                    var entity = new UnTrackedEntity();
                    entity.setFilename(path.toString());
                    entity.setFileSize(path.toFile().length());
                    entity.setFileHandler(handler.getClass().toString());
                    try {
                        InputStream is = Files.newInputStream(path);
                        String md5 = DigestUtils.md5Hex(is);
                        entity.setHashId(md5);
                    } catch (Exception ex) {

                    }
                    unTrackedDao.insert(entity);

                } catch (Exception ex) {
                    var exception = new ExceptionFileEntity();
                    exception.setHashId(path.toString());
                    exception.setException(ex.toString());
                    exception.setFileName(path.toString());
                    exceptionFileRepository.save(exception);
                    logger.error("Error during process file {} with handler: {}", path.getFileName(), handler.getClass().getSimpleName());
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
