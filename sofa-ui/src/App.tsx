import React from 'react';
import './App.css';
import {StatsPanel} from "./components/stats_panel";
import {ChakraProvider, Container} from "@chakra-ui/react"
import {AudioPlayerComponent} from "./components/audio_player_component";
import {AlbumsPanel} from "./components/artists_panel";

function App() {
    return (
        <ChakraProvider>
            <Container>
                <div className="App">
                    <StatsPanel/>
                    <AlbumsPanel/>
                    <AudioPlayerComponent track={undefined}/>

                </div>
            </Container>
        </ChakraProvider>

    );
}

export default App;
