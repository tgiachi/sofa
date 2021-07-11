import {RootStore} from "../store/root_store";
import {Flex, Heading} from "@chakra-ui/react";
import {SearchComponent} from "./search_component";
import React from "react";

export const Footer = ({rootStore} : {rootStore: RootStore}) => {
    return (<Flex justify="space-between" borderBottom="1px" borderColor="gray.200" p={3}>
        <Heading as="h3" size="lg" m={1}>
            Sofa Streamer
        </Heading>
        <Heading as="h3" size="lg" m={1}>

        </Heading>
    </Flex>)
}