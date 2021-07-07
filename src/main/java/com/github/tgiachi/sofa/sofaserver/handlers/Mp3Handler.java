package com.github.tgiachi.sofa.sofaserver.handlers;

import com.github.tgiachi.sofa.sofaserver.annotations.FileTypeHandler;
import com.github.tgiachi.sofa.sofaserver.dao.AlbumDao;
import com.github.tgiachi.sofa.sofaserver.dao.ArtistDao;
import com.github.tgiachi.sofa.sofaserver.dao.GenreDao;
import com.github.tgiachi.sofa.sofaserver.dao.TrackDao;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.entities.GenreEntity;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.exceptions.UnTrackedException;
import com.github.tgiachi.sofa.sofaserver.interfaces.IFileTypeHandler;
import com.mpatric.mp3agic.Mp3File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@FileTypeHandler(extension = "mp3")

public class Mp3Handler implements IFileTypeHandler {

    private final ArtistDao artistDao;
    private final AlbumDao albumDao;
    private final TrackDao trackDao;
    private final GenreDao genreDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Mp3Handler(ArtistDao artistDao, AlbumDao albumDao, TrackDao trackDao, GenreDao genreDao) {
        this.artistDao = artistDao;
        this.albumDao = albumDao;
        this.trackDao = trackDao;
        this.genreDao = genreDao;
    }

    @Override
    public void processFile(Path file) throws Exception {

        var mp3File = new Mp3File(file.toFile());

        if (mp3File.hasId3v2Tag()) {

            var artist = new ArtistEntity();
            artist.setName(mp3File.getId3v2Tag().getArtist().trim());

            artist = artistDao.insert(artist);

            var album = new AlbumEntity();
            album.setArtist(artist);
            album.setName(mp3File.getId3v2Tag().getAlbum());
            album.setYear(mp3File.getId3v2Tag().getYear());

            album = albumDao.insert(album);

            var track = new TrackEntity();
            track.setFilename(file.toString());

            track.setTrackName(mp3File.getId3v2Tag().getTitle());
            track.setBitrate(mp3File.getBitrate());
            if (mp3File.getId3v2Tag().getTrack() != null || !mp3File.getId3v2Tag().getTrack().equals("")) {
                track.setTrackOrder(Integer.parseInt(mp3File.getId3v2Tag().getTrack().split("/")[0]));
            }

            track.setTrackLength(mp3File.getLengthInMilliseconds());
            track.setFileSize(file.toFile().length());
            track.setAlbum(album);

            InputStream is = Files.newInputStream(file);
            String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);

            track.setHashId(md5);

            if (mp3File.getId3v2Tag().getGenreDescription() != null || !mp3File.getId3v2Tag().getGenreDescription().equals("")) {

                var genre = new GenreEntity();
                genre.setName(mp3File.getId3v2Tag().getGenreDescription());
                genre = genreDao.insert(genre);
                track.setGenre(genre);
            }

            trackDao.insert(track);

            logger.info("Added to db {} - {} - {} GENERE {} ", artist.getName(), album.getName(), track.getTrackName(), mp3File.getId3v2Tag().getGenreDescription());

        } else {
            throw new UnTrackedException();
        }

    }
}
