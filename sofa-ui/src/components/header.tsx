import react from "react";
import {Flex, Heading, Link} from "@chakra-ui/react";
import {SearchComponent} from "./search_component";
import React from "react";
import {RootStore} from "../store/root_store";

export const Header = ({rootStore} : {rootStore: RootStore}) => {
    return (<Flex justify="space-between" borderBottom="1px" borderColor="gray.200" p={3}>
        <Heading as="h3" size="lg" m={1}>
           Sofa Streamer
        </Heading>
        <Heading as="h3" size="lg" m={1}>
            <SearchComponent context={rootStore} />
        </Heading>
    </Flex>)
}