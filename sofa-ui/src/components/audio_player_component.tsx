import {useEffect, useState} from "react";
import {GetStreamUrl} from "../api/api.routes";
import {TrackEntity} from "../api/api.interfaces";
import {observer} from "mobx-react";
import {Box, Center, HStack, Image, Text} from "@chakra-ui/react";
import AudioPlayer from 'react-h5-audio-player';
import 'react-h5-audio-player/lib/styles.css';
import {useStore} from "../store/useStore";

export const AudioPlayerComponent = observer(({track}: { track?: TrackEntity }) => {
    const [hash, setHash] = useState("");
    const [albumUrl, setAlbumUrl] = useState("");
    const {rootStore} = useStore();
    const albumStore = rootStore.albumStore;

    useEffect(() => {
        console.log(`${track}`)
        console.log("SELECTED " + rootStore.playerStore.currentTrack)
        setHash(rootStore.playerStore.currentTrack?.hashId || "");
        if (rootStore.playerStore.currentTrack) {
            rootStore.albumStore.findAlbumById((rootStore.playerStore.currentTrack as TrackEntity).albumHashId).then(data => {
                setAlbumUrl(data?.coverUrl || "");
            })
        }
    }, [rootStore.playerStore.currentTrack])


    return (
        <HStack p={3}>
            <Box>
                <Center w={"150px"} h={"150px"}>
                    <Image src={albumUrl} w={150} h={150}/>
                </Center>
            </Box>
            <Box>
                <AudioPlayer showDownloadProgress showFilledVolume autoPlay
                             src={GetStreamUrl(hash)}/>
            </Box>
            <Box>
                <Center>

                    <Text
                        fontSize={"5xl"}>{rootStore.playerStore.currentTrack?.artistName} - {rootStore.playerStore.currentTrack?.trackName}</Text>
                </Center>
            </Box>
        </HStack>
    )
});