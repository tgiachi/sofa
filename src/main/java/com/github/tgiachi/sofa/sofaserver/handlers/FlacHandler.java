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
import com.github.tgiachi.sofa.sofaserver.interfaces.handlers.IFileTypeHandler;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@FileTypeHandler(extension = "flac")
public class FlacHandler implements IFileTypeHandler {

    private final ArtistDao artistDao;
    private final AlbumDao albumDao;
    private final TrackDao trackDao;
    private final GenreDao genreDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public FlacHandler(ArtistDao artistDao, AlbumDao albumDao, TrackDao trackDao, GenreDao genreDao) {
        this.artistDao = artistDao;
        this.albumDao = albumDao;
        this.trackDao = trackDao;
        this.genreDao = genreDao;
    }

    @Override
    public void processFile(Path file) throws Exception {
        var audioFile = AudioFileIO.read(file.toFile());
        var tag = audioFile.getTag();

        if (tag == null)
            throw new UnTrackedException();

        var artist = new ArtistEntity();
        artist.setName(tag.getFirst(FieldKey.ARTIST));

        artist = artistDao.insert(artist);

        var album = new AlbumEntity();
        album.setArtist(artist);
        album.setName(tag.getFirst(FieldKey.ALBUM));
        album.setYear(tag.getFirst(FieldKey.YEAR));

        album = albumDao.insert(album);

        var track = new TrackEntity();
        track.setFilename(file.toString());

        track.setTrackName(tag.getFirst(FieldKey.TITLE));
        track.setBitrate(Integer.parseInt(audioFile.getAudioHeader().getBitRate()));
        if (tag.getFirst(FieldKey.TRACK) != null && !tag.getFirst(FieldKey.TRACK).equals("")) {
            track.setTrackOrder(Integer.parseInt(tag.getFirst(FieldKey.TRACK).split("/")[0]));
        }

        track.setTrackLength(audioFile.getAudioHeader().getTrackLength());
        track.setFileSize(file.toFile().length());
        track.setAlbum(album);
        track.setAlbumHashId(album.getHashId());
        track.setArtistName(artist.getName());
        track.setArtistHashId(artist.getHashId());


        InputStream is = Files.newInputStream(file);
        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);

        track.setHashId(md5);

        if (tag.getFirst(FieldKey.GENRE) != null && !tag.getFirst(FieldKey.GENRE).equals("")) {

            var genresText = tag.getFirst(FieldKey.GENRE).replace(',', ';').split(";");
            for (var genreText : genresText) {
                var genre = new GenreEntity();
                genre.setName(genreText.trim());
                genre = genreDao.insert(genre);
                track.getGenre().add(genre);
            }

        }

        trackDao.insert(track);

        logger.info("Added to db {} - {} - {}", artist.getName(), album.getName(), track.getTrackName());

    }

    @Override
    public boolean fileExists(String hashId, boolean force) {
        if (force) return false;
        return trackDao.findByHashId(hashId) != null;
    }
}
