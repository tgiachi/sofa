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
}