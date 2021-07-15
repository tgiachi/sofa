import React from 'react';
import "./App.scss"
import {Col, Container, Row} from "react-bootstrap";
import {Header} from "./layout/Header";
import {Footer} from "./layout/Footer";

import {BrowserRouter as Router} from "react-router-dom";
import {Routes} from "./Routes";

function App() {


    return (
        <>
            <Router>
                <Header/>
                <main  className={"flex-shrink-0 ui"}>
                    <Container fluid>
                        <Row >

                            <Col>
                                <Routes/>
                            </Col>
                            {/*<Col sm="12"></Col>*/}
                        </Row>
                    </Container>
                </main>
                <footer className={"mt-auto footer py-3 fixed-bottom"}>
                    <Footer/>
                </footer>
            </Router>
        </>
    );
}

export default App;
