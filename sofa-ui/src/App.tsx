import React, {createContext} from 'react';
import './App.css';
import {StatsPanel} from "./components/stats_panel";
import {ChakraProvider, Container} from "@chakra-ui/react"
import {AudioPlayerComponent} from "./components/audio_player_component";
import {AlbumsPanel} from "./components/albums_panel";
import {Grid, GridItem, Flex, Button, Box, CSSReset} from "@chakra-ui/react"
import {SearchComponent} from "./components/search_component";
import {PlayerStore} from "./store/player_store";
import {RootStore} from "./store/root_store";
import {Header} from "./components/header";
import {ThemeProvider} from "@emotion/react";
import {customTheme} from "./theme";
import {Footer} from "./components/footer";


function App() {
    const rootStore = new RootStore();
    return (
        <ThemeProvider theme={customTheme}>
            <CSSReset />
            <ChakraProvider>
                <Header rootStore={rootStore}/>
                <Container>
                    <div className="App">
                        <StatsPanel/>
                        {/*<AlbumsPanel/>*/}

                        <AudioPlayerComponent context={rootStore} track={undefined}/>

                    </div>
                </Container>
                <Footer rootStore={rootStore} />
            </ChakraProvider>
        </ThemeProvider>

    );
}

export default App;
