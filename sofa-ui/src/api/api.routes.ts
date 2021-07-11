export const ApiBaseUrl = "http://localhost:8080/api/v1";

export const StatsRoute = ApiBaseUrl + "/statistics"
export const AllAlbumRouter = ApiBaseUrl + "/albums/all";
export const GetStreamUrl = (hashId: string) => {
    console.log(`Full stream url: ${ ApiBaseUrl + "/stream/" + hashId}`)
    return ApiBaseUrl + "/stream/" + hashId
};
export const ArtistById = ApiBaseUrl + "/artists/id";
export const AlbumsById = ApiBaseUrl + "/albums/id";

export const AutocompleteRoute = ApiBaseUrl + "/indexes/autocomplete";