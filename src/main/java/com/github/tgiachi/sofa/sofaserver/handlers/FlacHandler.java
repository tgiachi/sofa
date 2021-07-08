package com.github.tgiachi.sofa.sofaserver.handlers;

import com.github.tgiachi.sofa.sofaserver.annotations.FileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.interfaces.handlers.IFileTypeHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.nio.file.Path;

@Service
@FileTypeHandler(extension = "flac")
public class FlacHandler implements IFileTypeHandler {
    @Override
    public void processFile(Path file) throws Exception {

    }

    @Override
    public boolean fileExists(String hashId, boolean force) {
        return false;
    }
}
