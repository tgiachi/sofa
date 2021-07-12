import {TrackEntity} from "../api/api.interfaces";
import {StackDivider, VStack} from "@chakra-ui/react";
import {TrackItem} from "./track_item";

export const TracksLists = ({tracks, onClick}: { tracks?: TrackEntity[]; onClick: (track: TrackEntity) => void }) => {
    return (<VStack
        divider={<StackDivider borderColor="gray.200"/>}
        spacing={4}
        align="stretch"
    >
        {tracks?.map(value => {
            return (
                <TrackItem onClick={onClick} key={value.hashId + value.trackName} track={value}/>
            )
        })}
    </VStack>)
}