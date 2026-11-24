
package ru.marsdlc.client.theme;

public class ThemeManager {

    public enum Theme {
        RAINBOW,
        ASTOLFO,
        SUNSET,
        OCEAN,
        GALAXY,
        INFERNO
    }

    private static Theme currentTheme = Theme.GALAXY;

    private static final int[][] RAINBOW = {
        {255, 70, 70},
        {255, 180, 50},
        {80, 230, 100},
        {60, 180, 255},
        {160, 80, 255},
        {255, 70, 180}
    };

    private static final int[][] ASTOLFO = {
        {255, 110, 190},
        {150, 180, 255},
        {255, 110, 190}
    };

    private static final int[][] SUNSET = {
        {255, 80, 100},
        {255, 150, 60},
        {255, 70, 170}
    };

    private static final int[][] OCEAN = {
        {30, 100, 255},
        {40, 230, 220},
        {30, 100, 255}
    };

    private static final int[][] GALAXY = {
        {120, 60, 255},
        {210, 70, 255},
        {50, 100, 255}
    };

    private static final int[][] INFERNO = {
        {255, 40, 40},
        {255, 130, 20},
        {255, 50, 70}
    };

    public static void init() {
        currentTheme = Theme.GALAXY;
    }

    public static void setTheme(Theme theme) {
        currentTheme = theme;
    }

    public static Theme getTheme() {
        return currentTheme;
    }

    public static int getColor(float offset) {
        int[][] colors = getPalette();

        double time = System.currentTimeMillis() / 1800.0;
        double position = (time + offset) % colors.length;

        int index = (int) position;
        float progress = (float) (position - index);

        int[] first = colors[index];
        int[] second = colors[(index + 1) % colors.length];

        int r = (int) (first[0] + (second[0] - first[0]) * progress);
        int g = (int) (first[1] + (second[1] - first[1]) * progress);
        int b = (int) (first[2] + (second[2] - first[2]) * progress);

        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }

    private static int[][] getPalette() {
        return switch (currentTheme) {
            case RAINBOW -> RAINBOW;
            case ASTOLFO -> ASTOLFO;
            case SUNSET -> SUNSET;
            case OCEAN -> OCEAN;
            case GALAXY -> GALAXY;
            case INFERNO -> INFERNO;
        };
    }
}
