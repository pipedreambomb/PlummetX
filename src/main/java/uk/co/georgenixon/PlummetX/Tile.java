package uk.co.georgenixon.PlummetX;

/**
 * Created by g on 30/08/14.
 */
public class Tile {
    private TileOpacity opacity;
    private Integer value;

    public Tile(Integer value, TileOpacity opacity) {
        this.value = value;
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

    // Break apart or disappear depending on opacity, e.g.
    // X -> x
    // x -> 6
    // 6 -> <empty>
    public void destruct() {
        this.opacity = TileOpacity.partial;
    }
}
