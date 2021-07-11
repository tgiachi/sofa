import { theme } from "@chakra-ui/react";
export const customTheme = {
    ...theme,
    fonts: {
        body: "system-ui, sans-serif",
        heading: "Georgia, serif",
        mono: "Menlo, monospace"
    },
    colors: {
        ...theme.colors,
        brand: {
            900: "#1a365d",
            800: "#153e75",
            700: "#2a69ac"
        },
        teal: {
            500: "#319795"
        },
        dandelion: {
            100: "#FFE066",
            200: "FFDA49"
        }
    }
};