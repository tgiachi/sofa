import ReactAudioPlayer from 'react-audio-player';
import {useEffect, useState} from "react";
import {GetStreamUrl} from "../consts/api.routes";
import {TrackEntity} from "../api/api.interfaces";

export const AudioPlayerComponent = ({track}: { track?: TrackEntity }) => {

    const [hash, setHash] = useState(track?.hashId);
    useEffect(() => {
        if (track)
            setHash(GetStreamUrl(track.hashId));
    }, [track])

    return (
        <ReactAudioPlayer src={hash} autoPlay controls/>
    )
}