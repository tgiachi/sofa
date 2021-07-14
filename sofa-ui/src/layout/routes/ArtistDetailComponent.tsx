import {observer} from "mobx-react";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useStore} from "../../store/useStore";
import {Col, Container, Image, Row} from "react-bootstrap";
import {ArtistEntity} from "../../api/api.interfaces";

export const ArtistDetailComponent = observer(() => {

    const params = useParams() as any;
    const {artistStore} = useStore().rootStore;
    const [artist, setArtist] = useState<ArtistEntity | undefined>();

    useEffect(() => {

        artistStore.findArtistById(params.id).then(data => {
            if (data) {
                console.log(`Selected artist ${artist?.name}`);
                setArtist(artist);
            }
        })
    }, [params.id]);

    return (
        <Container fluid>
            <Row>
                <Col sm={2}>
                    <Image width={300} src={artist?.coverUrl}/>
                </Col>
                <Col sm={10}>

                </Col>
            </Row>
        </Container>)

});