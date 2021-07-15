import {observer} from "mobx-react";
import {AudioPlayer} from "../components/AudioPlayer";
import {useStore} from "../store/useStore";
import {Container, Navbar} from "react-bootstrap";

export const Footer = observer(() => {
    const {queueStore} = useStore().rootStore;
    return (
        <Navbar bg={"dark"} variant={"dark"}>
            <Container >
                <AudioPlayer track={queueStore.currentTrack}/>
            </Container>
        </Navbar>

    )
});