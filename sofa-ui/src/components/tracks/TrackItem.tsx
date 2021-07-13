import {observer} from "mobx-react";
import {TrackEntity} from "../../api/api.interfaces";
import {Button, Col, Container, Row} from "react-bootstrap";
import {Play, Stack} from "react-bootstrap-icons";
import {AlbumImage} from "../albums/AlbumImage";
import {useStore} from "../../store/useStore";

const convertToTimestamp = (ms: number): string => {


    let seconds = Math.floor(ms / 1000 % 60);
    let minutes = Math.floor(((ms / (1000 * 60)) % 60));


    const strMinutes = (minutes < 10) ? "0" + minutes : minutes;
    const strSeconds = (seconds < 10) ? "0" + seconds : seconds;

    return strMinutes + ":" + strSeconds;

}

export const TrackItem = observer(({track}: { track?: TrackEntity }) => {


    const {queueStore} = useStore().rootStore;
    return <tr>
        <td>
            <Button onClick={event => {
                if (track)
                    queueStore.playTrack(track);
            }
            } size={"sm"}>
                <Play/>
            </Button>
            <Button onClick={event => {
                if (track)
                    queueStore.addQueue(track)
            }
            } size={"sm"}>
                <Stack/>
            </Button>
        </td>
        <td>
            <Container>
                <Row>
                    <Col md={2}>
                        <AlbumImage albumId={track?.albumHashId}/>
                    </Col>
                    <Col md={10}>
                        {track?.artistName}
                    </Col>

                </Row>
            </Container>

        </td>
        <td>
            {track?.trackName}
        </td>
        <td>
            {convertToTimestamp((track?.trackLength || 1) * 1000)}
        </td>
    </tr>
});