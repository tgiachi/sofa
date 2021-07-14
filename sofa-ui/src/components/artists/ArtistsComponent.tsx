import {observer} from "mobx-react";
import {useEffect, useState} from "react";
import {ArtistEntity} from "../../api/api.interfaces";
import {useStore} from "../../store/useStore";
import {Col, Container, Row} from "react-bootstrap";
import {ArtistItem} from "./ArtistItem";

export const ArtistsComponent = observer(() => {
    const {artistStore} = useStore().rootStore;
    const [artistsCache, setArtistsCache] = useState<ArtistEntity[]>([]);

    useEffect(() => {
        if (artistStore.artists) {
            setArtistsCache(Array.from(artistStore.artists.values()));
        }
    }, [artistStore.artists])

    return <Container fluid>
        <Row xs={1} md={4} className="g-5">
            {artistsCache.map(artist => {
                return (<Col >
                    <ArtistItem key={artist?.id} artist={artist}/>
                </Col>)
            })}
        </Row>
    </Container>
});