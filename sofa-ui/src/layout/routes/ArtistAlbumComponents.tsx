import {observer} from "mobx-react";
import {useParams} from "react-router-dom";
import {AlbumComponent} from "../../components/albums/AlbumComponent";
import {useStore} from "../../store/useStore";
import {useEffect, useState} from "react";
import {AlbumEntity} from "../../api/api.interfaces";


export const ArtistAlbumComponents = observer(() => {
    const params = useParams() as any;
    const id = params.id;
    console.log("ARTIST ALBUM " +id );


    const {artistStore} = useStore().rootStore;
    artistStore.findAlbums(id);
    useEffect(() => {
        setAlbums(artistStore.albums);
    }, [artistStore.albums])
    const [albums, setAlbums] = useState<AlbumEntity[] | undefined>([]);



    return (<AlbumComponent albums={albums} />)
});