import {observer} from "mobx-react";
import {Button, Container, Row, Table} from "react-bootstrap";
import {TrackEntity} from "../../api/api.interfaces";
import {useEffect, useState} from "react";
import {TrackItem} from "./TrackItem";
import {ChevronCompactLeft, ChevronCompactRight} from "react-bootstrap-icons";

const pageSize = 20;

export const TracksTable = observer(({tracks}: { tracks?: TrackEntity[] }) => {
    const [page, setPage] = useState(1);
    const [paginatedTracks, setPaginatedTracks] = useState<TrackEntity[]>([]);
    useEffect(() => {
        if (tracks) {
            setPaginatedTracks(tracks.slice(page * pageSize, page * pageSize + pageSize));
        }
    }, [page, tracks]);


    return (
        <Container fluid>
            <Row sm={12}>
                <Table striped bordered hover size="sm" responsive>

                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Artist</th>
                        <th>Track name</th>
                        <th>Duration</th>
                    </tr>
                    </thead>
                    <tbody>
                    {paginatedTracks?.map(t => {
                        return (
                            <TrackItem track={t} key={t.id + t.hashId}/>
                        )
                    })}
                    </tbody>
                </Table>
            </Row>
            <Row sm={12}>
                <Button size={"sm"} onClick={() => setPage(page - 1)}><ChevronCompactLeft/></Button>
                <Button size={"sm"} onClick={() => setPage(page + 1)}><ChevronCompactRight/></Button>
            </Row>

        </Container>
    )

});