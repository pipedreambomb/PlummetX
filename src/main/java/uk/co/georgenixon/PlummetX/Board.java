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

    private Board(int sizeX, int sizeY, int maxTileValue) {
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

    public static Board createBlank(int sizeX, int sizeY, int maxTileValue) {
        return new Board(sizeX, sizeY, maxTileValue);
    }

    public void addTile(int i, Tile insertedTile) throws ColumnFullException {
        if (insertedTile.getValue() == 100000) {
            throw new IllegalArgumentException("Max tile value on this board is 1. This tile's value is 100000.");
        }
        if (tiles[0][0].isBlank() == false) {
            throw new ColumnFullException("Column 0 is full.");
        }
        tiles[0][0] = Tile.createOpaque(1);
    }

//    public static Board createHardcore(int sizeX, int sizeY, int maxTileValue) {
//        Board board = new Board(sizeX, sizeY, maxTileValue);
//        board.addTile()
//    }
}
