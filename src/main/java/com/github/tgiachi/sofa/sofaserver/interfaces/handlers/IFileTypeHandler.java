package com.github.tgiachi.sofa.sofaserver.interfaces.handlers;

import java.nio.file.Path;

public interface IFileTypeHandler {

    void processFile(Path file) throws Exception;

    boolean fileExists(String hashId, boolean force);
}