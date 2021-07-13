import {observer} from "mobx-react";
import {TrackEntity} from "../api/api.interfaces";
import {Card, Col, Container, Image, Row} from "react-bootstrap";
import H5AudioPlayer from "react-h5-audio-player";
import {useEffect, useState} from "react";
import {GetStreamUrl} from "../api/api.routes";
import {useStore} from "../store/useStore";
import 'react-h5-audio-player/src/styles.scss'


export const AudioPlayer = observer(({track}: { track?: TrackEntity }) => {

    const {albumStore, queueStore} = useStore().rootStore;
    const [currentStream, setCurrentStream] = useState("");
    const [albumCover, setAlbumCover] = useState("");

    const [title, setTitle] = useState("");

    useEffect(() => {
        if (track) {
            setCurrentStream(GetStreamUrl(track.hashId))
            albumStore.findAlbumById(track.albumHashId).then(data => {
                if (data?.coverUrl)
                    setAlbumCover(data?.coverUrl);
            })
            if (track?.trackName)
                setTitle(track?.trackName);
        }
    }, [track])


    return (
        <Container fluid>
            <Row className="align-items-center">
                <Col sm="2">
                    <Image width={100} height={100} src={albumCover}/>
                </Col>
                <Col sm="2"> {title}</Col>
                <Col sm="8">
                    <Card>
                        <H5AudioPlayer layout={"stacked"} onEnded={e => queueStore.onTrackEnd()} autoPlay
                                       src={currentStream}/>
                    </Card>
                </Col>


            </Row>
        </Container>
    )
});