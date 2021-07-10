import {action, makeAutoObservable} from "mobx"
export class PlayerStore {



    currentTrackHashId = ""

    get trackHashId () {return this.currentTrackHashId};

    constructor() {
        makeAutoObservable(this)
    }

    @action
    public playTrack(hashId: string): void {
        console.log("Store select")
        this.currentTrackHashId = hashId;
    }

}