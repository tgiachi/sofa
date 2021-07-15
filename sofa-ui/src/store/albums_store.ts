import {action, makeAutoObservable} from "mobx";
import {AlbumEntity} from "../api/api.interfaces";
import {AlbumsById} from "../api/api.routes";

export class AlbumsStore {
    albumcache: Map<String, AlbumEntity> = new Map<String, AlbumEntity>();

    get albums() {
        return this.albumcache;
    }

    constructor() {
        makeAutoObservable(this)
    }

    @action
    public async findAlbumById(hashId: string): Promise<AlbumEntity | undefined> {
        if (!this.albumcache.has(hashId)) {
            const artistJson = await (await fetch(AlbumsById + `/${hashId}`)).json();
            const artist = artistJson.data;
            this.albumcache.set(hashId, artist)
        }
        return this.albumcache.get(hashId);
    }


}