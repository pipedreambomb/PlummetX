package uk.co.georgenixon.PlummetX;

import java.io.PrintStream;

/**
 * Created by g on 30/08/14.
 */
public class ConsoleView implements View {

    private final PrintStream out;
    private final Board board;

    public ConsoleView(PrintStream printStream, Board board) {
        this.out = printStream;
        this.board = board;
    }

    @Override
    public void render() {
        Tile[] line;
        while ((line = board.nextLine()) != null) {
            StringBuilder sb = new StringBuilder("|");
            for (Tile tile : line) {
                sb.append(tile.getVisibleValue());
                sb.append("|");
            }
            out.println(sb.toString());
        }
    }

}
