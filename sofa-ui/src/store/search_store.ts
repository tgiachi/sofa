import {action, makeAutoObservable} from "mobx";
import {SearchResultEntity} from "../api/api.interfaces";
import {AutocompleteRoute} from "../api/api.routes";

export class SearchStore {

    mSearchResult: SearchResultEntity | undefined;

    get searchResults(): SearchResultEntity | undefined {
        if (this.mSearchResult) {
            return this.mSearchResult;
        }

        return undefined;
    }

    @action
    public search(text: string) {
        fetch(`${AutocompleteRoute}?text=${text}`).then(res => res.json()).then(data => {
            this.mSearchResult = data as SearchResultEntity;
        })
    }

    constructor() {
        this.mSearchResult = undefined;
        makeAutoObservable(this);
    }
}