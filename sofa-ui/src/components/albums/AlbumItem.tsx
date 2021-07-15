import {Card} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap";
import {AlbumEntity} from "../../api/api.interfaces";
import {observer} from "mobx-react";

export const AlbumItem = observer(({album}: { album?: AlbumEntity }) => {
    return (
        <Card>
            <Card.Img width={150} src={album?.coverUrl || "https://via.placeholder.com/150"}/>
            <Card.Title>{album?.name}</Card.Title>
            <LinkContainer to={`/artist/tracks/${album?.hashId}`}>
                <Card.Link> View tracks </Card.Link>
            </LinkContainer>
            <Card.Body>
            </Card.Body>
        </Card>
    )
});