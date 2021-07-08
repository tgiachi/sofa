package com.github.tgiachi.sofa.sofaserver.interfaces;

import java.nio.file.Path;

public interface IFileTypeHandler {

    void processFile(Path file) throws Exception;
}
