package com.github.tgiachi.sofa.sofaserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto implements Serializable {

    private long totalTracks;

    private long totalPlaylists;

    private long totalArtists;

    private String totalAudioLength;

    private String totalFileSize;

}
