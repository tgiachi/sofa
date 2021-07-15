import {action, makeAutoObservable} from "mobx"
import {TrackEntity} from "../api/api.interfaces";

export class PlayerStore {


    currentTrackHashId = ""
    currentTrack: TrackEntity | undefined;

    get trackHashId () {return this.currentTrackHashId};

    constructor() {
        makeAutoObservable(this)
        this.currentTrack = undefined;
    }

    @action
    public playTrack(hashId: string | TrackEntity): void {
        console.log("Store select ")
        console.log(hashId);
        if (hashId instanceof String) {
            this.currentTrackHashId = hashId as string;
        }
        else {
            this.currentTrack = hashId as TrackEntity;
            this.currentTrackHashId = (hashId as TrackEntity).hashId;
        }
    }

}