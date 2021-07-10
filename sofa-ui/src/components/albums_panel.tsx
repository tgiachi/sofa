import {VStack} from "@chakra-ui/react"
import {useEffect, useState} from "react";
import {AlbumEntity} from "../api/api.interfaces";
import {AlbumItem} from "./album_item"
import {AllAlbumRouter} from "../api/api.routes";

export const AlbumsPanel = () => {

    const [albums, setAlbums] = useState<AlbumEntity[]>([]);

    useEffect(() => {
        if (albums.length === 0) {
            fetch(AllAlbumRouter).then(res => res.json()).then(data => {
                console.log(data);
                setAlbums(data.data as AlbumEntity[]);
            })
        }
    }, [albums]);

    return (<VStack>
        {albums.length > 0 && albums.map(value => {
            return <AlbumItem album={value} key={value.hashId}/>
        })}
    </VStack>)

}