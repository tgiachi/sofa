import {makeAutoObservable} from "mobx";
import {PlayerStore} from "./player_store";
import {AlbumsStore} from "./albums_store";
import {ArtistsStore} from "./artists_store";
import {SearchStore} from "./search_store";
import {PlaylistStore} from "./playlists_store";
import {QueueStore} from "./queue_store";

export class RootStore {

    playerStore: PlayerStore;
    albumStore: AlbumsStore;
    artistStore: ArtistsStore;
    searchStore: SearchStore;
    playlistStore: PlaylistStore;
    queueStore: QueueStore;

    constructor() {
        makeAutoObservable(this)
        this.playerStore = new PlayerStore();
        this.albumStore = new AlbumsStore();
        this.artistStore = new ArtistsStore();
        this.searchStore = new SearchStore();
        this.playlistStore = new PlaylistStore();
        this.queueStore = new QueueStore();
        console.log("Store created");
        this.artistStore.getAll();
        this.playlistStore.getPlaylists()
        Promise.all([
            new Promise<string>(resolve => {
                console.log("Getting all artists")
                this.artistStore.getAll();
                resolve("");
            }),
            new Promise<string>(resolve => {
                console.log("Getting all playlists")
                this.playlistStore.getPlaylists();
                resolve("");
            })
        ]).then(value => {
            console.log("Prefetch ok")
        })


    }
}