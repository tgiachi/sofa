import {action, makeAutoObservable} from "mobx";
import {ArtistEntity} from "../api/api.interfaces";
import {ArtistById} from "../api/api.routes";

export class ArtistsStore {
    artistsCache: Map<String, ArtistEntity> = new Map<String, ArtistEntity>();

    get artists() {
        return this.artistsCache;
    }

    constructor() {
        makeAutoObservable(this)
    }

    @action
    public async findArtistById(hashId: string): Promise<ArtistEntity | undefined> {
        if (!this.artistsCache.has(hashId)) {
            const artistJson = await (await fetch(ArtistById + `/${hashId}`)).json();
            const artist = artistJson.data;
            this.artistsCache.set(hashId, artist)
        }
        return this.artistsCache.get(hashId);
    }


}