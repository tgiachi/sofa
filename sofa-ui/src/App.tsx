import React, {createContext} from 'react';
import './App.css';
import {StatsPanel} from "./components/stats_panel";
import {ChakraProvider, Container} from "@chakra-ui/react"
import {AudioPlayerComponent} from "./components/audio_player_component";
import {AlbumsPanel} from "./components/albums_panel";
import {Grid, GridItem, Flex, Button, Box} from "@chakra-ui/react"
import {SearchComponent} from "./components/search_component";
import {PlayerStore} from "./store/player_store";


function App() {
    const playerStore = new PlayerStore();
    return (
        <ChakraProvider>

            <Container>
                <div className="App">
                    {/*<StatsPanel/>*/}
                    {/*<AlbumsPanel/>*/}
                    <SearchComponent context={playerStore} />
                    <AudioPlayerComponent context={playerStore} track={undefined}/>

                </div>
            </Container>
        </ChakraProvider>

    );
}

export default App;
