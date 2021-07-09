import {TrackEntity} from "../api/api.interfaces";
import {Box, Grid} from "@chakra-ui/react"

export const TrackItem = ({track}: { track: TrackEntity }) => {
    return (
        <Grid templateColumns="repeat(2, 1fr)" gap={6}>
            <Box w="20%" h="10" bg="blue.500"/>
            <Box w="80%" h="10" bg="blue.500"/>
        </Grid>
    )
}