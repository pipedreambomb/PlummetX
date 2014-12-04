package uk.co.georgenixon.PlummetX;

/**
 * Created by g on 30/08/14.
 */
public class Board {
    private final int sizeX;
    private final int sizeY;
    private final int maxTileValue;
    private Tile[][] tiles;
    private int tileIterationCursor = 0;

    public Board(int sizeX, int sizeY, int maxTileValue) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.maxTileValue = maxTileValue;

        setUpTiles();
    }

    private void setUpTiles() {
        tiles = new Tile[sizeY][sizeX];

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                tiles[i][j] = Tile.createBlank();
            }
        }
    }

    public Tile[] nextLine() {
        return tileIterationCursor < sizeY ? tiles[tileIterationCursor++] : null;
    }

    public int columnWidth() {
        return Integer.toString(maxTileValue).length();
    }
}
