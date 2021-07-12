import {RootStore} from "./root_store";
import {createContext, useContext} from "react";

const rootStore = new RootStore();

export const StoreContext = createContext({
    rootStore
});

export const useStore = () => {
    const store = useContext(StoreContext);

    if (!store) {
        // this is especially useful in TypeScript so you don't need to be checking for null all the time
        throw new Error("useStore must be used within a StoreProvider.");
    }
    return store;
}