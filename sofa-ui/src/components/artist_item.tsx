import react from "react";
import {Avatar, Box, Grid, Text} from "@chakra-ui/react";
import {ArtistEntity} from "../api/api.interfaces";

export const ArtistItem = ({artist} : {artist: ArtistEntity}) => {
    return (<Grid templateColumns="repeat(2, 1fr)" style={{width: '800px'}} gap={6}>
        <Box w="80%" h="10" bg="blue.500">
            <Text>{artist.name}</Text>
        </Box>
    </Grid>)
}