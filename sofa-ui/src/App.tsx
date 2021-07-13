import React from 'react';
import "./App.scss"

import {useStore} from "./store/useStore";
import {Col, Container, Row} from "react-bootstrap";
import {Header} from "./layout/Header";
import {SideBar} from "./layout/SideBar";
import {MainContent} from "./layout/MainContent";
import {Footer} from "./layout/Footer";

function App() {

    const {queueStore} = useStore().rootStore;
    return (
        <>
            <Header/>
            <main className={"flex-shrink-0"}>
                <Container fluid>
                    <Row>
                        <Col sm="2">
                            <SideBar/>
                        </Col>
                        <Col sm="10">
                            <MainContent/>
                        </Col>
                        {/*<Col sm="12"></Col>*/}
                    </Row>
                </Container>
            </main>
            <footer className={"mt-auto footer py-3"}>
                <Footer/>
            </footer>
        </>
    );
}

export default App;
