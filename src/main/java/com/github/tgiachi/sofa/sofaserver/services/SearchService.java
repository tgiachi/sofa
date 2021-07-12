package com.github.tgiachi.sofa.sofaserver.services;

import com.github.tgiachi.sofa.sofaserver.dto.AutocompleteResult;
import com.github.tgiachi.sofa.sofaserver.entities.AlbumEntity;
import com.github.tgiachi.sofa.sofaserver.entities.ArtistEntity;
import com.github.tgiachi.sofa.sofaserver.entities.PlaylistMasterEntity;
import com.github.tgiachi.sofa.sofaserver.entities.TrackEntity;
import com.github.tgiachi.sofa.sofaserver.interfaces.entities.IBaseEntity;
import com.github.tgiachi.sofa.sofaserver.services.base.BaseService;
import com.github.tgiachi.sofa.sofaserver.utils.ReflectionUtils;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class SearchService extends BaseService {

    private final FullTextEntityManager fullTextEntityManager;

    private final EntityManager entityManager;
    private final Executor executor;
    public final List<Class<?>> indexedClasses = new ArrayList<>();


    public SearchService(@Qualifier("entityManagerFactory") EntityManager entityManager, @Qualifier("taskExecutor") Executor executor, FullTextEntityManager fullTextEntityManager) {

        this.entityManager = entityManager;
        this.executor = executor;
        this.fullTextEntityManager = fullTextEntityManager;
        indexedClasses.addAll(ReflectionUtils.getAnnotation(Indexed.class));
    }

    public void rebuildIndexes() {
        logger.info("Purge indexes");
        indexedClasses.forEach(c -> {
            logger.info("Deleting index: {}", c.getSimpleName());
            fullTextEntityManager.purgeAll(c);
        });
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (Exception ex) {
            logger.error("Error during re-index", ex);
        }
    }

    @Transactional
    public AutocompleteResult autocomplete(String text) {
        logger.info("Starting full-search for {}", text);
        var result = new AutocompleteResult();
        var artists = fullTextSearchFuture(ArtistEntity.class, "name", text);
        var tracks = fullTextSearchFuture(TrackEntity.class, "trackName", text);
        var albums = fullTextSearchFuture(AlbumEntity.class, "name", text);
        var playlists = fullTextSearchFuture(PlaylistMasterEntity.class, "name", text);

        CompletableFuture.allOf(artists, tracks, albums, playlists).join();

        try {
            result.setArtists(artists.get());
            result.setPlaylists(playlists.get());
            result.setTracks(tracks.get());
            result.setAlbums(albums.get());
        } catch (Exception ex) {
            logger.error("Error during autocomplete", ex);
        }

        logger.info("End full-search for {}", text);

        return result;
    }

    @Transactional
    <TEntity extends IBaseEntity> CompletableFuture<List<TEntity>> fullTextSearchFuture(Class<TEntity> classz, String fieldName, String text) {
        return CompletableFuture.completedFuture(fullTextSearch(classz, fieldName, text));
    }

    @Transactional
    <TEntity extends IBaseEntity> List<TEntity> fullTextSearch(Class<TEntity> classz, String fieldName, String text) {
        var ftem = Search.getFullTextEntityManager(entityManager);

        var query = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(classz)
                .get()
                .keyword()
                .wildcard()
                .onField(fieldName)
                .matching(String.format("*%s*", text))
                .createQuery();
        var results = (List<TEntity>) ftem.createFullTextQuery(query, classz).setMaxResults(10).getResultList();
        return results;
    }
}
