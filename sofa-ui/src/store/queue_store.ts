import {TrackEntity} from "../api/api.interfaces";
import {action, makeAutoObservable} from "mobx";


export class QueueStore {
    mTracks: TrackEntity[];
    mCurrentTrack: TrackEntity | undefined;
    mCurrentIndex: number;


    get tracks() {
        return this.mTracks;
    }

    get currentTrack() {
        return this.mCurrentTrack;
    }

    @action
    public onTrackEnd() {
        console.log("TRACK END");
    }

    @action
    public addQueue(track: TrackEntity) {
        this.mTracks.push(track);
    }

    constructor() {
        makeAutoObservable(this)

        this.mTracks = [];
        this.mCurrentIndex = 0;

    }
}