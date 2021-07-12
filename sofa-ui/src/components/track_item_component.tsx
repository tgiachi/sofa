import {observer} from "mobx-react";
import {AlbumEntity, TrackEntity} from "../api/api.interfaces";
import {Avatar, Box, Center, HStack, Td, Text, Tr} from "@chakra-ui/react";
import {useStore} from "../store/useStore";
import {useEffect, useState} from "react";
import {ArrowForwardIcon, ArrowUpIcon} from '@chakra-ui/icons'


const formatDuration = (s: number): string => {
    s = s * 1000;
    const ms = s % 1000;
    s = (s - ms) / 1000;
    const secs = s % 60;
    s = (s - secs) / 60;
    const mins = s % 60;

    return mins + ':' + secs;

}

export const TrackItemComponent = observer(({track}: { track?: TrackEntity }) => {


    const [album, setAlbum] = useState<AlbumEntity | undefined>();

    useEffect(() => {
        albumStore.findAlbumById(track?.albumHashId || "").then(data => {
            if (data) {
                setAlbum(data);
            }
        })
    }, [track?.albumHashId])

    const {albumStore, playerStore, queueStore} = useStore().rootStore;

    return (<Tr>
        <Td> <Box>
            <Center>
                <ArrowForwardIcon boxSize={"10"} onClick={event => {
                    if (track)
                        playerStore.playTrack(track);
                }}/>
                <ArrowUpIcon onClick={event => {
                    if (track)
                        queueStore.addQueue(track);
                }} />
            </Center>
        </Box> </Td>
        <Td>{track?.trackOrder}</Td>
        <Td> <Text>{track?.artistName}</Text> </Td>
        <Td><Text> {track?.trackName}</Text></Td>
        <Td>
            <HStack>
                <Box>
                    <Avatar src={album?.coverUrl}/>
                </Box>
                <Box>
                    <Text> {album?.name} </Text>
                </Box>
            </HStack>
        </Td>
        <Td>
            <Text>{formatDuration(track?.trackLength || 1)}</Text>
        </Td>
    </Tr>)
});