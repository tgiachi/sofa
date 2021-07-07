package com.github.tgiachi.sofa.sofaserver.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumAddedEvent {
    private long id;
}
