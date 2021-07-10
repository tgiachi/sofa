import react, {useEffect, useState} from "react"
import {Box, Container, Input} from "@chakra-ui/react";
import {SearchResultEntity} from "../api/api.interfaces";
import {AutocompleteRoute} from "../api/api.routes";
import {AlbumsPanel} from "./albums_panel";
import {AlbumList} from "./albums_list";
import {ArtistsList} from "./artists_list";
import {TracksLists} from "./tracks_list";
import {PlayerStore} from "../store/player_store";

export const SearchComponent = ({context} : {context: PlayerStore}) => {

    const [search, setSearch] = useState("");
    const [results, setResults] = useState<SearchResultEntity>();

    useEffect(() => {
        if (search && search.length > 3) {
            fetch(`${AutocompleteRoute}?text=${search}`).then(res => res.json()).then(data => {
                setResults(data);
                console.log(data);
            })
        }
    }, [search]);

    return (<Container>
            <Box w={300}>
            <Input placeholder={"input search"} onChange={(ev) => setSearch(ev.target.value)} />
        </Box>
        <Box>
            <AlbumList albums={results?.albums} />
            <ArtistsList artists={results?.artists} />
            <TracksLists onClick={(event) => {context.playTrack(event)}}  tracks={results?.tracks}/>
        </Box>

    </Container>)
}