import React from 'react';
import './App.css';
import {Box, ChakraProvider, CSSReset, Grid, GridItem, Tab, TabList, TabPanel, TabPanels, Tabs} from "@chakra-ui/react"
import {ThemeProvider} from "@emotion/react";
import {customTheme} from "./theme";
import {SearchResultComponent} from "./components/search_result_component";
import {AudioPlayerComponent} from "./components/audio_player_component";
import {Header} from "./components/header";
import {PlaylistComponent} from "./components/playlist_component";


function App() {

    return (
        <ThemeProvider theme={customTheme}>
            <CSSReset/>
            <ChakraProvider>

                <Grid
                    h="900"
                    w="300"
                    templateRows="repeat(8, 1fr)"
                    templateColumns="repeat(5, 1fr)"
                    gap={2}
                >
                    <GridItem rowSpan={8} colSpan={1}>
                        <Header/>
                    </GridItem>
                    <GridItem colSpan={4} rowSpan={6}>
                        <Box>
                            <Tabs>
                                <TabList>
                                    <Tab>Current</Tab>
                                    <Tab>Playlists</Tab>
                                </TabList>
                                <TabPanels>
                                    <TabPanel>
                                        <SearchResultComponent/>
                                    </TabPanel>
                                    <TabPanel>
                                        <PlaylistComponent/>
                                    </TabPanel>
                                </TabPanels>
                            </Tabs>

                        </Box>
                    </GridItem>

                    <GridItem colSpan={4} rowSpan={2}>
                        <Box flex={1}>
                            <AudioPlayerComponent track={undefined}/>
                        </Box>
                    </GridItem>
                </Grid>

                {/*<Header/>*/}

                {/*<Flex borderBottom="1px" borderColor="gray.200" p={1}>*/}

                {/*    <Tabs>*/}
                {/*        <TabList>*/}
                {/*            <Tab>Current</Tab>*/}
                {/*            <Tab>Playlists</Tab>*/}
                {/*        </TabList>*/}

                {/*        <TabPanels>*/}
                {/*            <TabPanel>*/}
                {/*                <SearchResultComponent/>*/}
                {/*            </TabPanel>*/}
                {/*            <TabPanel>*/}
                {/*                <PlaylistComponent/>*/}
                {/*            </TabPanel>*/}

                {/*        </TabPanels>*/}
                {/*    </Tabs>*/}


                {/*    /!*<AlbumsPanel/>*!/*/}


                {/*</Flex>*/}
                {/*<Footer/>*/}
            </ChakraProvider>
        </ThemeProvider>

    );
}

export default App;
