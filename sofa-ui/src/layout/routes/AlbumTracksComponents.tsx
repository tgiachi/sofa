import {observer} from "mobx-react";
import {useParams} from "react-router-dom";
import {useStore} from "../../store/useStore";
import {useEffect} from "react";
import {TracksTable} from "../../components/tracks/TracksTable";

export const AlbumTracksComponents = observer(() => {

    const {artistStore} = useStore().rootStore;
    const params = useParams() as any;

    const id = params.id;

    useEffect(() => {
        artistStore.findArtistAlbum(id);
    }, [id]);

    return <TracksTable tracks={artistStore.artistAlbum?.tracks || []}/>
})