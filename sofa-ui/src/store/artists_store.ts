import {action, makeAutoObservable} from "mobx";
import {ArtistEntity} from "../api/api.interfaces";
import {AllArtists, ArtistById, ArtistSimilarById} from "../api/api.routes";

export class ArtistsStore {
    artistsCache: Map<String, ArtistEntity> = new Map<String, ArtistEntity>();

    get artists() {
        return this.artistsCache;
    }

    constructor() {
        makeAutoObservable(this)
    }

    @action
    public getAll() {
        fetch(AllArtists).then(data => data.json()).then(res => {
            const arts = res.data as ArtistEntity[];

            arts.forEach(value => {
                this.artistsCache.set(value.hashId, value);
            })
        })
    }

    @action
    public async findArtistById(hashId: string): Promise<ArtistEntity | undefined> {
        console.log(`Search artist with id: ${hashId}`);
        if (!this.artistsCache.has(hashId)) {
            console.log(`Artist  ${hashId} not found in cache`)
            const artistJson = await (await fetch(ArtistById + `/${hashId}`)).json();
            const artist = artistJson.data;
            this.artistsCache.set(hashId, artist)
            return artist;
        }
        return this.artistsCache.get(hashId);
    }

    public async findSimilarArtistById(hashId: string): Promise<ArtistEntity[]> {
        const similarArtists = await (await fetch(ArtistSimilarById + `/${hashId}`)).json();
        return similarArtists.data as ArtistEntity[];
    }


}