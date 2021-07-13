import {action, makeAutoObservable} from "mobx";
import {SearchResultEntity} from "../api/api.interfaces";
import {AutocompleteRoute} from "../api/api.routes";
import {throttle} from 'throttle-typescript';

export class SearchStore {

    mSearchResult: SearchResultEntity | undefined;
    mSearchText: string;

    get searchResults(): SearchResultEntity | undefined {
        if (this.mSearchResult) {
            return this.mSearchResult;

        }

        return undefined;
    }

    @action
    public search(text: string) {
        this.mSearchText = text;
        this.fetchResult();
    }

    @action
    private fetchResult() {
        console.log("Starting search")
        if (this.mSearchText.length >= 3) {
            fetch(`${AutocompleteRoute}?text=${this.mSearchText}`).then(res => res.json()).then(data => {
                console.log(`Search result: ${data}`)
                this.mSearchResult = data as SearchResultEntity;
            })
        }
    }

    constructor() {
        this.mSearchResult = undefined;
        this.mSearchText = "";
        makeAutoObservable(this);
    }
}