import ReactAudioPlayer from 'react-audio-player';
import {useCallback, useEffect, useState} from "react";
import {GetStreamUrl} from "../api/api.routes";
import {TrackEntity} from "../api/api.interfaces";
import {observer} from "mobx-react";
import {PlayerStore} from "../store/player_store";
import {AlbumsStore} from "../store/albums_store";
import {RootStore} from "../store/root_store";
import {Image, Text} from "@chakra-ui/react";

export const AudioPlayerComponent = observer(({track, context}: { track?: TrackEntity, context: RootStore }) => {
    const [hash, setHash] = useState("");
    const [albumUrl, setAlbumUrl] = useState("");
    const albumStore = new AlbumsStore();

    useEffect(() => {
        console.log(`${track}`)
        console.log("SELECTED " + context.playerStore.currentTrack)
        setHash(context.playerStore.currentTrack?.hashId || "");
        if (context.playerStore.currentTrack) {
            context.albumStore.findAlbumById((context.playerStore.currentTrack as TrackEntity).albumHashId).then(data => {
                setAlbumUrl(data?.coverUrl || "");
            })
        }
    }, [context.playerStore.currentTrack])


    return (
        <>
        <ReactAudioPlayer src={GetStreamUrl(hash)} autoPlay controls/>
            <Image src={albumUrl} />
            <Text>{context.playerStore.currentTrack?.artistName} - {context.playerStore.currentTrack?.trackName}</Text>
        </>
    )
});