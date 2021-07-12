const currentUrl = window.location;
let apiUrl = "";
if (currentUrl.toString() === "http://localhost:3000/") {
    apiUrl = "http://localhost:8080";
} else {
    apiUrl = currentUrl.toString();
}

export const ApiBaseUrl = `${apiUrl}/api/v1`;

export const StatsRoute = ApiBaseUrl + "/statistics"
export const AllAlbumRouter = ApiBaseUrl + "/albums/all";
export const GetStreamUrl = (hashId: string) => {
    console.log(`Full stream url: ${ApiBaseUrl + "/stream/" + hashId}`)
    return ApiBaseUrl + "/stream/" + hashId
};
export const ArtistById = ApiBaseUrl + "/artists/id";
export const AlbumsById = ApiBaseUrl + "/albums/id";
export const AllPlaylists = ApiBaseUrl + "/playlists/all";

export const AutocompleteRoute = ApiBaseUrl + "/indexes/autocomplete";