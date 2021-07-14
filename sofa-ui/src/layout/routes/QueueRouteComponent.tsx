import {observer} from "mobx-react";
import {useStore} from "../../store/useStore";
import {TracksTable} from "../../components/tracks/TracksTable";

export const QueueRouteComponent = observer(() => {
    const {queueStore} = useStore().rootStore;
    return <TracksTable tracks={queueStore.tracks}/>
});