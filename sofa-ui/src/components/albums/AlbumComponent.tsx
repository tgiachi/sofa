import {observer} from "mobx-react";
import {Col, Container, Row} from "react-bootstrap";
import {ArtistItem} from "../artists/ArtistItem";
import {AlbumEntity} from "../../api/api.interfaces";
import {AlbumItem} from "./AlbumItem";

export const AlbumComponent = observer(({albums} : {albums?: AlbumEntity[]}) => {


    return <Container fluid>

        <Row xs={1} md={4} className="g-5">
            {(albums  || [])?.map(album => {

                return (<Col >
                    <AlbumItem key={album?.id} album={album}/>
                </Col>)
            })}
        </Row>
    </Container>
});