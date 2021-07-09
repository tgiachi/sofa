export const ApiBaseUrl = "http://localhost:8080/api/v1";

export const StatsRoute = ApiBaseUrl + "/statistics"
export const AllAlbumRouter = ApiBaseUrl + "/albums/all";
export const GetStreamUrl = (hashId: string) => {
    return ApiBaseUrl + "/stream/" + hashId
};