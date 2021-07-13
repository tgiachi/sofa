import {observer} from "mobx-react";
import {AudioPlayer} from "../components/AudioPlayer";
import {useStore} from "../store/useStore";
import {Card} from "react-bootstrap";

export const Footer = observer(() => {
    const {queueStore} = useStore().rootStore;
    return (
        <Card>
            <Card.Body>
                <AudioPlayer track={queueStore.currentTrack}/>
            </Card.Body>
        </Card>
    )
});