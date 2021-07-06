package com.github.tgiachi.sofa.sofaserver.handlers;

import com.github.tgiachi.sofa.sofaserver.annotations.FileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.dao.ArtistDao;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.IFileTypeHandler;
import com.mpatric.mp3agic.Mp3File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@FileTypeHandler(extension = "mp3")

public class Mp3Handler implements IFileTypeHandler {

    private final ArtistDao artistDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Mp3Handler(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public void processFile(Path file) throws Exception {

        var mp3File = new Mp3File(file.toFile());

        if (mp3File.hasId3v1Tag()) {
            logger.info("{} - {} ", mp3File.getId3v1Tag().getArtist(), mp3File.getId3v1Tag().getTitle());

            var entity = new ArtistEntity();
            entity.setName(mp3File.getId3v1Tag().getArtist().trim());

            artistDao.insert(entity);

        }

    }
}
