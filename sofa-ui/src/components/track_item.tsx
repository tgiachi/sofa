import {ArtistEntity, TrackEntity} from "../api/api.interfaces";
import {Avatar, Box, Flex, Link} from "@chakra-ui/react"
import {useStore} from "../store/useStore";
import {useState} from "react";

export const TrackItem = ({track, onClick}: { track: TrackEntity, onClick: (hash: TrackEntity) => void }) => {
    const {artistStore} = useStore().rootStore;
    const [artist, setArtist] = useState<ArtistEntity>();
    if (track) {
        artistStore.findArtistById(track.artistHashId).then(value => {
            if (value) {
                setArtist(value);
            }
        })
    }

    return (
        <Flex>
            <Box w="20%">
                <Avatar src={artist?.coverUrl}/>
            </Box>
            <Box w="80%" alignContent={"baseline"} h="40px">
                <Link onClick={event => onClick(track)}>  {track.artistName} - {track.trackName}</Link>
            </Box>
        </Flex>


    )
}