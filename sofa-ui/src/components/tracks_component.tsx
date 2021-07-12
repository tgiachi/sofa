import {Table, Tbody, Th, Thead, Tr,} from "@chakra-ui/react"
import {observer} from "mobx-react";
import {TrackEntity} from "../api/api.interfaces";
import {TrackItemComponent} from "./track_item_component";

export const TracksComponent = observer(({tracks}: { tracks?: TrackEntity[] }) => {
    return (<Table variant="striped" size="sm">
        <Thead>
            <Tr>
                <Th>
                    #
                </Th>
                <Th>
                    #
                </Th>
                <Th>
                    Artist
                </Th>
                <Th>
                    Title
                </Th>
                <Th>
                    Album
                </Th>
                <Th>
                    Duration
                </Th>
            </Tr>
        </Thead>
        <Tbody>
            {tracks?.map(t => {
                return <TrackItemComponent key={t.hashId} track={t}/>
            })}
        </Tbody>
    </Table>)
});