import {ArtistEntity} from "../../api/api.interfaces";
import {Card} from "react-bootstrap";
import {LinkContainer} from 'react-router-bootstrap'

export const ArtistItem = ({artist}: { artist?: ArtistEntity }) => {
    return (
        <Card>
            <Card.Img width={150} src={artist?.coverUrl || "https://via.placeholder.com/150"}/>
            <Card.Title>{artist?.name}</Card.Title>
            <Card.Body>
                <LinkContainer to={`/artist/${artist?.hashId}`} >
                <Card.Link >
                    View
                </Card.Link>
                </LinkContainer>
            </Card.Body>
        </Card>
    )
}