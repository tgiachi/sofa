export interface StatsResponse {
    totalTracks: number;
    totalPlayLists: number;
    totalArtists: number;
    totalAudioLength: string;
    totalFileSize: string;
}

export interface BaseEntity {
    id: number;
    hashId: string;
}

export interface TrackEntity extends BaseEntity {
    filename: string;
    trackOrder: number;
    fileSize: number;
    trackName: string;
    trackLength: number;
    bitrate: number;
    playCount: number;
    albumHashId: string;
    artistName: string;
    artistHashId: string;
}

export interface AlbumEntity extends BaseEntity {
    coverUrl: string;
    name: string;
    year: number;
    artist: ArtistEntity;
    tracks: TrackEntity[]
}

export interface ArtistEntity extends BaseEntity {
    name: string;
    coverUrl: string;
}

export interface PlaylistEntity extends BaseEntity {
    name: string,
    coverUrl: string
}

export interface SearchResultEntity extends BaseEntity {
    tracks: TrackEntity[],
    albums: AlbumEntity[],
    artists: ArtistEntity[],
    playlists: PlaylistEntity[]
}

export interface PlaylistEntity extends BaseEntity {
    name: string;
    coverUrl: string;
    tracks: PlaylistDetailEntity[]
}
export interface PlaylistDetailEntity extends  BaseEntity {
    trackEntity: TrackEntity;
    trackOrder: number;
}