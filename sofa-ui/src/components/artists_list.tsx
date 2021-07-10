import react from "react";
import {ArtistEntity} from "../api/api.interfaces";
import {Box} from "@chakra-ui/react";
import {AlbumItem} from "./album_item";
import {ArtistItem} from "./artist_item";

export const ArtistsList = ({artists}: { artists?: ArtistEntity[] }) => {
    return (<Box>
        {artists?.map(value => {
            return (
                <ArtistItem key={value.hashId} artist={value}/>
            )
        })}
    </Box>)
}