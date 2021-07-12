import React from "react";
import {Heading, VStack} from "@chakra-ui/react";
import {SearchComponent} from "./search_component";

export const Header = () => {


    return (<VStack justify="space-between" borderBottom="1px" borderColor="gray.200" p={3}>
        <Heading as="h3" size="lg" m={1}>
            Sofa Streamer
        </Heading>
        <Heading as="h3" size="lg" m={1}>
            <SearchComponent/>
        </Heading>
    </VStack>)
}