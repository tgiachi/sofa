import {useEffect, useState} from "react";
import {StatsResponse} from "../api/api.interfaces";
import {StatsRoute} from "../consts/api.routes";
import {Stack, Stat, StatLabel, StatNumber} from "@chakra-ui/react"

export const StatsPanel = () => {

    const [stats, setStats] = useState<StatsResponse>()
    useEffect(() => {
        if (stats === undefined) {
            const results = fetch(StatsRoute).then(res => res.json()).then(data => {
                setStats(data as StatsResponse);
            })
        }
    })
    return (<Stack direction="row">
        <Stat>
            <StatLabel>Artists</StatLabel>
            <StatNumber>{stats?.totalArtists}</StatNumber>
        </Stat>
        <Stat>
            <StatLabel>Tracks</StatLabel>
            <StatNumber>{stats?.totalTracks}</StatNumber>
        </Stat>
        <Stat>
            <StatLabel>Audio length</StatLabel>
            <StatNumber>{stats?.totalAudioLength}</StatNumber>
        </Stat>
        <Stat>
            <StatLabel>Total file size</StatLabel>
            <StatNumber>{stats?.totalFileSize}</StatNumber>
        </Stat>
        <Stat>
            <StatLabel>Playlists</StatLabel>
            <StatNumber>{stats?.totalPlayLists || 0}</StatNumber>
        </Stat>


    </Stack>)
}