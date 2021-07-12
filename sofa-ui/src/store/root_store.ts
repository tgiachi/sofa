import {makeAutoObservable} from "mobx";
import {PlayerStore} from "./player_store";
import {AlbumsStore} from "./albums_store";
import {ArtistsStore} from "./artists_store";
import {SearchStore} from "./search_store";
import {PlaylistStore} from "./playlists_store";

export class RootStore {

    playerStore: PlayerStore;
    albumStore: AlbumsStore;
    artistStore: ArtistsStore;
    searchStore: SearchStore;
    playlistStore: PlaylistStore;

    constructor() {
        makeAutoObservable(this)
        this.playerStore = new PlayerStore();
        this.albumStore = new AlbumsStore();
        this.artistStore = new ArtistsStore();
        this.searchStore = new SearchStore();
        this.playlistStore = new PlaylistStore();
    }
}