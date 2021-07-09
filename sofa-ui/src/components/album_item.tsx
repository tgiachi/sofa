import {AlbumEntity} from "../api/api.interfaces";
import {Avatar, Box, Grid, Text} from "@chakra-ui/react"

export const AlbumItem = ({album}: { album: AlbumEntity }) => {
    return (<Grid templateColumns="repeat(2, 1fr)" style={{width: '800px'}} gap={6}>
        <Box w="20%" h="10" bg="blue.500">
            <Avatar name={album.name} src={album.coverUrl}/>
        </Box>
        <Box w="80%" h="10" bg="blue.500">
            <Text>{album.artist.name} - {album.name}</Text>
        </Box>
    </Grid>)

}