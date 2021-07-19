package com.github.tgiachi.sofa.sofaserver.services;

import com.github.tgiachi.sofa.sofaserver.annotations.FileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.dao.UnTrackedDao;
import com.github.tgiachi.sofa.sofaserver.entities.DirectoryWatchEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ExceptionFileEntity;
import com.github.tgiachi.sofa.sofaserver.entities.UnTrackedEntity;
import com.github.tgiachi.sofa.sofaserver.exceptions.UnTrackedException;
import com.github.tgiachi.sofa.sofaserver.interfaces.handlers.IFileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.repository.DirectoryWatchRepository;
import com.github.tgiachi.sofa.sofaserver.repository.ExceptionFileRepository;
import com.github.tgiachi.sofa.sofaserver.services.base.BaseService;
import com.github.tgiachi.sofa.sofaserver.utils.Md5Utils;
import com.github.tgiachi.sofa.sofaserver.utils.ReflectionUtils;
import io.methvin.watcher.DirectoryChangeEvent;
import io.methvin.watcher.DirectoryChangeListener;
import io.methvin.watcher.DirectoryWatcher;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class ScanService extends BaseService implements DirectoryChangeListener {


    private final HashMap<String, Class<?>> handlers = new HashMap<>();

    private final List<DirectoryWatcher> directoryWatchers = new ArrayList<>();

    private final Executor executor;
    private final ApplicationContext applicationContext;
    private final UnTrackedDao unTrackedDao;
    private final ExceptionFileRepository exceptionFileRepository;
    private final DirectoryWatchRepository directoryWatchRepository;

    public ScanService(@Qualifier("taskExecutor") Executor taskExecutor, ApplicationContext applicationContext, UnTrackedDao unTrackedDao, ExceptionFileRepository exceptionFileRepository, DirectoryWatchRepository directoryWatchRepository) {
        this.executor = taskExecutor;
        this.applicationContext = applicationContext;
        this.unTrackedDao = unTrackedDao;
        this.exceptionFileRepository = exceptionFileRepository;
        this.directoryWatchRepository = directoryWatchRepository;
    }


    @PostConstruct
    public void init() {
        ReflectionUtils.getAnnotation(FileTypeHandler.class).forEach(a -> {
            var annotation = a.getAnnotation(FileTypeHandler.class);
            logger.info("Adding handler for {} ({})", annotation.extension(), a.getSimpleName());
            handlers.put(annotation.extension(), a);
        });

        createDirectoryWatchers();
    }

    private void createDirectoryWatchers() {

        directoryWatchRepository.findAll().forEach(d -> {
            logger.info("Creating directory watcher for {}", d.getDirectory());

            try {
                startDirectoryWatch(Path.of(d.getDirectory()));
            } catch (Exception ex) {
                logger.error("Error during listen directory: {}", d.getDirectory(), ex);
            }

        });

    }

    private void startDirectoryWatch(Path path) throws Exception {
        directoryWatchers.add(DirectoryWatcher.builder().path(path).listener(this).build());
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

    private void addIfNotExists(String directory) {
        var entity = directoryWatchRepository.findByDirectory(directory);
        if (entity == null) {
            entity = new DirectoryWatchEntity();
            entity.setDirectory(directory);
            entity.setCreatedDateTime(LocalDateTime.now());
            entity.setUpdatedDateTime(LocalDateTime.now());
            directoryWatchRepository.save(entity);
            try {
                startDirectoryWatch(Path.of(entity.getDirectory()));
            } catch (Exception ex) {
                logger.error("Error during start directory watch: {}", directory, ex);
            }
        }
    }

    public void scanDirectory(String directory) {
        addIfNotExists(directory);
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

    @Override
    public void onEvent(DirectoryChangeEvent event) throws IOException {
        switch (event.eventType()) {
            case CREATE: {
                logger.info("Created new file : {}", event.path());
                getFileInfo(event.path());
            }
        }
    }
}
