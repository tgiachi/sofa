import {observer} from "mobx-react";
import {useParams} from "react-router-dom";
import {AlbumComponent} from "../../components/albums/AlbumComponent";
import {useStore} from "../../store/useStore";
import {useEffect} from "react";


export const ArtistAlbumComponents = observer(() => {
    const {artistStore} = useStore().rootStore;
    const params = useParams() as any;
    const id = params.id;
    console.log("ARTIST ALBUM " + id);

    useEffect(() => {
        artistStore.findAlbums(id);
    }, [id])


    return (<AlbumComponent albums={artistStore.albums}/>)
});