import {useEffect, useState} from "react"
import {Box, Container, Input} from "@chakra-ui/react";
import {useStore} from "../store/useStore";

export const SearchComponent = () => {

    const {rootStore} = useStore();
    const [search, setSearch] = useState("");


    useEffect(() => {
        if (search && search.length >= 3) {
            rootStore.searchStore.search(search);
        }
    }, [search]);

    return (<Container>
        <Box>
            <Input placeholder={"input search"} onChange={(ev) => setSearch(ev.target.value)}/>
        </Box>
        {/*<Box>*/}
        {/*    <AlbumList albums={results?.albums}/>*/}
        {/*    <ArtistsList artists={results?.artists}/>*/}
        {/*    <TracksLists onClick={(event) => {*/}
        {/*        rootStore.playerStore.playTrack(event)*/}
        {/*    }} tracks={results?.tracks}/>*/}
        {/*</Box>*/}

    </Container>)
}