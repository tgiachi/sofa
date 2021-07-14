package com.github.tgiachi.sofa.sofaserver.rest.entities;

import com.github.tgiachi.sofa.sofaserver.dao.ArtistDao;
import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponse;
import com.github.tgiachi.sofa.sofaserver.data.SofaRestResponseType;
import com.github.tgiachi.sofa.sofaserver.dto.AlbumDto;
import com.github.tgiachi.sofa.sofaserver.dto.ArtistDto;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.mappers.AlbumMapper;
import com.github.tgiachi.sofa.sofaserver.mappers.ArtistMapper;
import com.github.tgiachi.sofa.sofaserver.processors.LastFmProcessor;
import com.github.tgiachi.sofa.sofaserver.rest.base.AbstractBaseRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistRestController extends AbstractBaseRestController<ArtistEntity, ArtistDto, ArtistDao, ArtistMapper> {

    private final LastFmProcessor lastFmProcessor;
    private final AlbumMapper albumMapper;

    public ArtistRestController(ArtistMapper mapper, ArtistDao artistDao, LastFmProcessor lastFmProcessor, AlbumMapper albumMapper) {
        super(mapper, artistDao);
        this.lastFmProcessor = lastFmProcessor;
        this.albumMapper = albumMapper;
    }

    @GetMapping("/similar/{id}")
    public Mono<SofaRestResponse<List<ArtistDto>>> getSimilarArtist(@PathVariable("id") String artistId) {
        var results = new ArrayList<ArtistEntity>();
        var artist = repository.findByHashId(artistId);
        var similarArtists = lastFmProcessor.getSimilarArtist(artist.getName());

        similarArtists = similarArtists.subList(0, 5);
        similarArtists.forEach(s -> {
            var art = repository.findArtistByName(s);
            if (art != null)
                results.add(art);
        });

        return Mono.just(SofaRestResponse.<List<ArtistDto>>builder().status(SofaRestResponseType.SUCCESS).data(mapper.toDto(results)).build());
    }

    @GetMapping("/albums/{id}")
    public Mono<SofaRestResponse<List<AlbumDto>>> getArtistsAlbums(@PathVariable("id") String artistId) {
        var results = repository.findArtistAlbums(artistId);
        return Mono.just(SofaRestResponse.<List<AlbumDto>>builder().status(SofaRestResponseType.SUCCESS).data(albumMapper.toDto(results)).build());
    }
}
