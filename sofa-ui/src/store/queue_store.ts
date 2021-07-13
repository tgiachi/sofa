import {TrackEntity} from "../api/api.interfaces";
import {action, makeAutoObservable} from "mobx";
import {setTimeout} from "timers";


export class QueueStore {
    mTracks: TrackEntity[];
    mCurrentTrack: TrackEntity | undefined;
    mCurrentIndex: number;
    mQueueEnabled: boolean;

    @action
    public playTrack(track: TrackEntity) {
        if (this.mTracks.filter(value => value.id === track.id).length === 0) {
            this.mCurrentIndex = this.mTracks.push(track);
        } else {
            this.mCurrentIndex = this.mTracks.indexOf(track);
        }
        this.mCurrentTrack = track;
    }


    get tracks() {
        return this.mTracks;
    }

    get currentTrack() {
        return this.mCurrentTrack;
    }

    @action
    public onTrackEnd() {
        console.log("TRACK END");
        setTimeout(args => {

            if (this.mQueueEnabled) {
                if (this.mCurrentIndex < this.mTracks.length) {
                    this.mCurrentIndex++
                    this.mCurrentTrack = this.mTracks[this.mCurrentIndex];
                    console.log(`Playing ${this.currentTrack?.trackName}`)
                }
            }
        }, 1000)
    }

    @action
    public addQueue(track: TrackEntity) {
        this.mTracks.push(track);
    }

    @action
    public next() {
        if (this.mCurrentIndex + 1 <= this.mTracks.length) {
            this.onTrackEnd();
        }
    }

    public startQueue() {
        this.onTrackEnd();
    }

    @action
    public prev() {
        if (this.mCurrentIndex - 1 >= 0) {
            this.mCurrentIndex--
            this.mCurrentTrack = this.mTracks[this.mCurrentIndex];
        }
    }

    constructor() {
        makeAutoObservable(this)

        this.mQueueEnabled = true;
        this.mTracks = [];
        this.mCurrentIndex = 0;

    }
}