package uk.co.georgenixon.PlummetX;

import java.io.PrintStream;

/**
 * Created by g on 30/08/14.
 */
public class ConsoleView implements View {

    private final PrintStream out;
    private final Board board;
    private final int columnWidth;

    public ConsoleView(PrintStream printStream, Board board) {
        this.out = printStream;
        this.board = board;
        // Only need to query this once, as it won't change.
        this.columnWidth = board.columnWidth();
    }

    @Override
    public void render() {
        Tile[] line;
        while ((line = board.nextLine()) != null) {
            StringBuilder sb = new StringBuilder("|");
            for (Tile tile : line) {
                sb.append(paddedTileValue(tile));
                sb.append("|");
            }
            out.println(sb.toString());
        }
    }

    private String paddedTileValue(Tile tile) {
        String paddedValue = tile.getVisibleValue();

        while (paddedValue.length() < columnWidth) {
            paddedValue = " " + paddedValue;
        }

        return paddedValue;
    }
}
