import {observer} from "mobx-react";
import {useStore} from "../../store/useStore";
import {Container, Row} from "react-bootstrap";
import {TracksTable} from "../../components/tracks/TracksTable";

export const SearchResultRouteComponent = observer(() => {
    const {searchStore} = useStore().rootStore;


    return <Container>
        <Row>
            <TracksTable tracks={searchStore.searchResults?.tracks}/>
        </Row>
    </Container>
})