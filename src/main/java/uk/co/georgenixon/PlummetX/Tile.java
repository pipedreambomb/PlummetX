package uk.co.georgenixon.PlummetX;

/**
 * Created by g on 30/08/14.
 */
public class Tile {
    private TileOpacity opacity;
    private Integer value;

    private Tile(Integer value, TileOpacity opacity) {
        this.value = value;
        this.opacity = opacity;
    }


    public static Tile createBlank() {
        return new Tile(0, TileOpacity.clear);
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

    public String getVisibleValue() {
        switch (this.opacity) {
            case opaque:
                return "X";
            case partial:
                return "x";
            default:
                return this.isEmpty() ? " " : value.toString();
        }
    }

    // Break apart or disappear depending on opacity, e.g.
    // X -> x
    // x -> 6
    // 6 -> <empty>
    public void destruct() {
        switch (this.opacity) {
            case opaque:
                this.opacity = TileOpacity.partial;
                break;
            case partial:
                this.opacity = TileOpacity.clear;
                break;
            default:
                this.value = 0;
        }
    }

    boolean isEmpty() {
        return this.value == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (opacity != tile.opacity) return false;
        if (!value.equals(tile.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = opacity.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public boolean isBlank() {
        return value == 0 && opacity == TileOpacity.clear;
    }

    public Integer getValue() {
        return value;
    }
}
