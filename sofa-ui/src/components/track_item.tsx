import {TrackEntity} from "../api/api.interfaces";
import {Box, Grid, Link} from "@chakra-ui/react"

export const TrackItem = ({track, onClick}: { track: TrackEntity, onClick: (hash: string) => void }) => {
    return (
        <Grid templateColumns="repeat(2, 1fr)" gap={6}>
            <Box w="100%" h="10" bg="blue.500">
                <Link onClick={event => onClick(track.hashId)} >  {track.trackName}</Link>

            </Box>

        </Grid>
    )
}