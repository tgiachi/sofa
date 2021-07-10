import ReactAudioPlayer from 'react-audio-player';
import {useCallback, useEffect, useState} from "react";
import {GetStreamUrl} from "../api/api.routes";
import {TrackEntity} from "../api/api.interfaces";
import {observer} from "mobx-react";
import {PlayerStore} from "../store/player_store";

export const AudioPlayerComponent = observer(({track, context}: { track?: TrackEntity, context: PlayerStore }) => {
    const [hash, setHash] = useState("");


    useEffect(() => {
        console.log("SELECTED " + context.trackHashId)
        setHash(context.trackHashId);
    }, [context.trackHashId])


    return (
        <ReactAudioPlayer src={GetStreamUrl(hash)} autoPlay controls/>
    )
});