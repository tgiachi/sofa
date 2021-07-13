import {observer} from "mobx-react";
import {Card} from "react-bootstrap";
import {useStore} from "../store/useStore";
import {AudioPlayer} from "../components/AudioPlayer";

export const SideBar = observer(() => {
    const {queueStore} = useStore().rootStore;
    return (
        <Card>

        </Card>
    )
});