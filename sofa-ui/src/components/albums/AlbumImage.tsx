import {Image, Spinner} from "react-bootstrap";
import {useStore} from "../../store/useStore";
import {useEffect, useState} from "react";

export const AlbumImage = ({albumId}: { albumId?: string }) => {

    const {albumStore} = useStore().rootStore;
    const [albumImage, setAlbumImage] = useState("");

    useEffect(() => {
        if (albumId)
            albumStore.findAlbumById(albumId).then(album => {
                if (album?.coverUrl)
                    setAlbumImage(album?.coverUrl);
            })
    }, [albumId]);

    if (!albumImage)
        return <Spinner animation={"border"} size={"sm"}  />

    return <Image src={albumImage} width={50} height={50}/>
}