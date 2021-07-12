import {action, makeAutoObservable, toJS} from "mobx";
import {AllPlaylists} from "../api/api.routes";
import {PlaylistEntity} from "../api/api.interfaces";

export class PlaylistStore {
    mPlaylists: PlaylistEntity[] | undefined;

    get playlists() {
        return this.mPlaylists;
    }

    @action
    public getPlaylists() {
        fetch(AllPlaylists).then(res => res.json()).then(data => {
            this.mPlaylists = data.data as PlaylistEntity[];
            console.log(toJS(this.mPlaylists));
        })
    }

    constructor() {
        makeAutoObservable(this);
        this.getPlaylists();
    }
}