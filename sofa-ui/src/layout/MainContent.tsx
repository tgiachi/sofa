import {observer} from "mobx-react";
import {TracksTable} from "../components/tracks/TracksTable";
import {useStore} from "../store/useStore";
import {Card} from "react-bootstrap";

export const MainContent = observer(() => {

    const {searchStore} = useStore().rootStore;

    return <>
        <Card>
            <Card.Body>
                <TracksTable tracks={searchStore.searchResults?.tracks}/>
            </Card.Body>
        </Card>

    </>
});