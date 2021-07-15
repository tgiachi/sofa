import {FormControl, Nav, Navbar} from "react-bootstrap";
import React from "react";
import {observer} from "mobx-react";
import {useStore} from "../store/useStore";
import {Link} from "react-router-dom";
import { useHistory } from "react-router-dom";

export const Header = observer(() => {

    const history = useHistory();
    const {searchStore} = useStore().rootStore;

    return (<Navbar bg="dark" variant="dark">
        <Navbar.Brand>
            Sofa streamer
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav"/>
        <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
                <Nav.Link>
                    <Link to={"/queue"}> Queue </Link>
                </Nav.Link>
                <Nav.Link>
                    <Link to={"/artists"}> Artists </Link>
                </Nav.Link>
                <Nav.Link>
                    <Link to={"/playlist"}> Playlists </Link>
                </Nav.Link>

            </Nav>

            <FormControl type="text" placeholder="Search" className="mr-sm-2"
                         onKeyPress={(event: any) => {
                             if (event.charCode === 13) {
                                 console.log("Searching for " + event.target.value);
                                 searchStore.search(event.target.value);
                                 history.push("/search");
                             }
                         }}
            />

        </Navbar.Collapse>
    </Navbar>)
});