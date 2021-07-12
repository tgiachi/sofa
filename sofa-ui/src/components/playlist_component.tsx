import {observer} from "mobx-react";
import {useStore} from "../store/useStore";
import {useEffect, useState} from "react";
import {PlaylistEntity} from "../api/api.interfaces";
import {Accordion, AccordionButton, AccordionItem, AccordionPanel, Box, Grid, GridItem, Image} from "@chakra-ui/react";
import {TracksComponent} from "./tracks_component";

export const PlaylistComponent = observer(() => {
    const {playlistStore} = useStore().rootStore;

    const [playlists, setPlaylists] = useState<PlaylistEntity[]>();

    useEffect(() => {
        if (playlistStore.playlists) {
            setPlaylists(playlistStore.playlists);
        }
    }, [playlistStore.playlists])

    return (<Accordion>
        {playlists?.map(p => {
            return (<AccordionItem>

                <h2>
                    <AccordionButton>
                        <Box flex={"1"} textAlign={"left"}>
                            <Grid templateColumns="repeat(5, 1fr)" gap={4}>
                                <GridItem colSpan={2} h="10" >
                                    {p.name}
                                </GridItem>
                                <GridItem colStart={4} colEnd={6} h="10"  >
                                    <Image w={10} h={10} src={p.coverUrl} />
                                </GridItem>
                            </Grid>

                        </Box>
                    </AccordionButton>
                </h2>
                <AccordionPanel pb={4}>
                    <TracksComponent tracks={p.tracks.map(s => s.trackEntity)}/>
                </AccordionPanel>

            </AccordionItem>)
        })}
    </Accordion>)
})