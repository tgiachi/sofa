import React, {useEffect} from "react";
import {useStore} from "../store/useStore";
import {observer} from "mobx-react";
import {TracksComponent} from "./tracks_component";

export const SearchResultComponent = observer(() => {
    const {rootStore} = useStore();
    useEffect(() => {

    }, [rootStore.searchStore.searchResults])

    return (


        <TracksComponent tracks={rootStore.searchStore.searchResults?.tracks}/>


        //   <ArtistsList   artists={rootStore.searchStore.searchResults?.artists}/>
        // </VStack>
    )

});