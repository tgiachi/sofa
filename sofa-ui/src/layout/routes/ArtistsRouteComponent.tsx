import {observer} from "mobx-react";
import {ArtistsComponent} from "../../components/artists/ArtistsComponent";
import {useStore} from "../../store/useStore";

export const ArtistsRouteComponent = observer(() => {
    const {artistStore} = useStore().rootStore;
    return <ArtistsComponent artists={Array.from(artistStore.artists.values())}/>
});