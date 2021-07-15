import {observer} from "mobx-react";
import {Col, Container, Row} from "react-bootstrap";
import {AlbumEntity} from "../../api/api.interfaces";
import {AlbumItem} from "./AlbumItem";
import {toJS} from "mobx";

export const AlbumComponent = observer(({albums} : {albums?: AlbumEntity[]}) => {
    console.log(`Album component `);
    console.log(toJS(albums));


    return <Container fluid>

        <Row xs={1} md={4} className="g-5">
            {(albums)?.map(album => {

                return (<Col >
                    <AlbumItem key={album?.id+album?.name} album={album}/>
                </Col>)
            })}
        </Row>
    </Container>
});