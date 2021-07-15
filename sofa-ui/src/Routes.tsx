import {Route, Switch} from "react-router-dom";
import {MainContent} from "./layout/MainContent";
import {ArtistsRouteComponent} from "./layout/routes/ArtistsRouteComponent";
import {QueueRouteComponent} from "./layout/routes/QueueRouteComponent";
import {ArtistDetailComponent} from "./layout/routes/ArtistDetailComponent";
import {ArtistAlbumComponents} from "./layout/routes/ArtistAlbumComponents";
import {AlbumTracksComponents} from "./layout/routes/AlbumTracksComponents";

export const Routes = () => {
    return (<Switch>

        <Route path="/artists" >
            <ArtistsRouteComponent />
        </Route>
        <Route path="/artist/tracks/:id" >
            <AlbumTracksComponents />
        </Route>
        <Route path="/artist/albums/:id" >
            <ArtistAlbumComponents />
        </Route>
        <Route path="/artist/:id" >
            <ArtistDetailComponent />
        </Route>
        <Route path="/search/" >
            <MainContent/>
        </Route>

        <Route path="/queue" >
            <QueueRouteComponent />
        </Route>
        <Route path="/">
            <MainContent/>
        </Route>
    </Switch>)
}