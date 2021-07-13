import {FormControl, Nav, Navbar} from "react-bootstrap";
import React from "react";
import {observer} from "mobx-react";
import {useStore} from "../store/useStore";

export const Header = observer(() => {

    const {searchStore} = useStore().rootStore;

    return (<Navbar bg="dark" variant="dark">
        <Navbar.Brand>
            Sofa streamer
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav"/>
        <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
            </Nav>

            <FormControl type="text" placeholder="Search" className="mr-sm-2" onChange={event => {
                searchStore.search(event.target.value);
            }}/>

        </Navbar.Collapse>
    </Navbar>)
});