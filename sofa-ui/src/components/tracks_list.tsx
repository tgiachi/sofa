import react from "react";
import {TrackEntity} from "../api/api.interfaces";
import {Box} from "@chakra-ui/react";
import {AlbumItem} from "./album_item";
import {TrackItem} from "./track_item";

export const TracksLists = ({tracks, onClick} : {tracks?: TrackEntity[]; onClick: (hash: string) => void}) =>{
    return (<Box>
        {tracks?.map(value => {
            return (
                <TrackItem onClick={onClick} key={value.hashId+value.trackName} track={value}/>
            )
        })}
    </Box>)
}