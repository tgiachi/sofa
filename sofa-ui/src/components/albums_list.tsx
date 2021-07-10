import react from "react";
import {AlbumEntity} from "../api/api.interfaces";
import {Box} from "@chakra-ui/react";
import {AlbumItem} from "./album_item";

export const AlbumList = ({albums}: { albums?: AlbumEntity[] }) => {
    return (<Box>
        {albums?.map(value => {
            return (
                <AlbumItem key={value.hashId+value.name} album={value}/>
            )
        })}
    </Box>)

}