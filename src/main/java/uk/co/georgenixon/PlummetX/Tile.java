package uk.co.georgenixon.PlummetX;

/**
 * Created by g on 30/08/14.
 */
public class Tile {
    private final TileOpacity opacity;
    private Integer value;

    void setValue(Integer value) {

//        if (value == 0)
//            throw new IllegalArgumentException("Zero is not a valid tile");
//
//        // We're using monospace single columns right now,
//        // so reject numbers that won't fit the view!
//        if (value < 0 || value > 9)
//            throw new IllegalArgumentException("Number is too wide!");

        this.value = value;
    }

    public Tile(Integer value, TileOpacity opacity) {
        setValue(value);
        this.opacity = opacity;
    }

    public static Tile createOpaque(int value) {
        return new Tile(value, TileOpacity.opaque);
    }

    public static Tile createPartiallyOpaque(int value) {
        return new Tile(value, TileOpacity.partial);
    }
    public static Tile createClear(Integer value) {
        return new Tile(value, TileOpacity.clear);
    }

    public char getVisibleValue() {
        switch (this.opacity) {
            case opaque:
                return 'X';
            case partial:
                return 'x';
            default:
                return valueToChar();
        }
    }

    private char valueToChar() {
        return value.toString().charAt(0);
    }

}
