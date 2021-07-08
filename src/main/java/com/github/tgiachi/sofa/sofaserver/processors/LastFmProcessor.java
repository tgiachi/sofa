package com.github.tgiachi.sofa.sofaserver.processors;

import com.github.tgiachi.sofa.sofaserver.events.AlbumAddedEvent;
import com.github.tgiachi.sofa.sofaserver.repository.AlbumRepository;
import de.umass.lastfm.Album;
import de.umass.lastfm.Caller;
import de.umass.lastfm.ImageSize;
import de.umass.lastfm.Track;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@Service
public class LastFmProcessor {

    private final AlbumRepository albumDao;

    @Value("${lastfm.api}")
    private String lastFmApi;

    @Value("${lastfm.secret}")
    private String lastFmSecret;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public LastFmProcessor(AlbumRepository albumDao) {
        this.albumDao = albumDao;
    }

    @PostConstruct
    public void onStart() {
        Caller.getInstance().setUserAgent("sofa");

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAlbumAdded(AlbumAddedEvent event) {
        var entity = albumDao.findById(event.getId()).get();
        if (!entity.getName().equals("")) {
            var a = Track.getInfo(entity.getArtist().getName(), "", lastFmApi);
            var albumInfo = Album.getInfo(entity.getArtist().getName(), entity.getName(), lastFmApi);
            if (albumInfo != null) {
                var url = albumInfo.getImageURL(ImageSize.LARGE);
                var calendar = Calendar.getInstance();
                calendar.setTime(albumInfo.getReleaseDate());
                entity.setYear(Integer.toString(calendar.get(Calendar.YEAR)));
                entity.setCoverUrl(url);
                albumDao.save(entity);
            }
        }
    }
}
