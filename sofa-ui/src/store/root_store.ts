import {makeAutoObservable} from "mobx";
import {PlayerStore} from "./player_store";
import {AlbumsStore} from "./albums_store";
import {ArtistsStore} from "./artists_store";

export class RootStore {

    playerStore: PlayerStore;
    albumStore: AlbumsStore;
    artistStore: ArtistsStore;

    constructor() {
        makeAutoObservable(this)
        this.playerStore = new PlayerStore();
        this.albumStore = new AlbumsStore();
        this.artistStore = new ArtistsStore();
    }
}