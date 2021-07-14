import {observer} from "mobx-react";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useStore} from "../../store/useStore";
import {Card, Col, Container, Image, Row} from "react-bootstrap";
import {ArtistEntity} from "../../api/api.interfaces";
import {ArtistsComponent} from "../../components/artists/ArtistsComponent";

export const ArtistDetailComponent = observer(() => {



    const params = useParams() as any;
    const {artistStore} = useStore().rootStore;
    const [artist, setArtist] = useState<ArtistEntity | undefined>();
    const [similarArtists, setSimilarArtists] = useState<ArtistEntity[]>();


    useEffect(() => {
        artistStore.findArtistById(params.id).then(data => {
            if (data) {
                console.log(`Selected artist ${data?.name}`);
                setArtist(data);
            }
        })
        artistStore.findSimilarArtistById(params.id).then(data => {
            setSimilarArtists(data);
        })

    }, [params.id]);

    return (
        <Container fluid>
            <Row>
                <Col sm={4}>
                    <Image width={300} src={artist?.coverUrl}/>
                </Col>
                <Col sm={8}>
                    {artist?.name}
                </Col>
            </Row>
            <Row>
                <Col>
                    <Card>
                        <Card.Title> Similar artists</Card.Title>
                        <Card.Body>
                            <ArtistsComponent artists={similarArtists} />
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>)

});