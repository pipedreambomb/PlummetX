package Tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import uk.co.georgenixon.PlummetX.Board;
import uk.co.georgenixon.PlummetX.ConsoleView;
import uk.co.georgenixon.PlummetX.Tile;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleViewTests {

    private ConsoleView sut;
    private PrintStream out;

    @Before
    public void setUp() throws Exception {
        out = mock(PrintStream.class);
    }

//    @After
//    public void tearDown() throws Exception {
//
//    }

    @Test
    public void two_by_one_Xs() throws Exception {

        // Arrange
        Board board = mock(Board.class);
        Tile tile = makeMockTile("X");
        Tile[] tiles = new Tile[] { tile, tile};
        when(board.nextLine()).thenReturn(tiles).thenReturn(null);
        sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out).println("|X|X|");
    }

    @Test
    public void one_by_one_X() throws Exception {

        // Arrange
        Tile tile = Tile.createOpaque(1);
        Tile[] tiles = new Tile[] { tile};
        Board board = makeMockBoard(tiles);
        sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out).println("|X|");
    }

    // Convenience method for creating a board without specifying column size
    private Board makeMockBoard(Tile[] tiles) {
        // We don't care what the max tile value is, so default to 1 digit columns
        return makeMockBoard(1, tiles);
    }

    @Test
    public void two_by_two_5s() throws Exception {

        // Arrange
        Tile tileWithValue5 = makeMockTile("5");
        Tile[] tileRow = {tileWithValue5, tileWithValue5};
        Board board = makeMockBoard(new Tile[][] {tileRow, tileRow});
        sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out, times(2)).println("|5|5|");
    }

    @Test
    public void checkerboard() throws Exception {

        //Arrange
        Tile X = makeMockTile("X");
        Tile x = makeMockTile("x");

        Board board = makeMockBoard(new Tile[][] {
                {X, x, X},
                {x, X, x},
                {X, x, X}}
        );

        sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out, times(2)).println("|X|x|X|");
        verify(out).println("|x|X|x|");
    }

    private Board makeMockBoard(Tile[][] tiles) {
        Board board = mock(Board.class);

        // Add a null tile list at end so iterating while loops terminate.
        List<Tile[]> answer = new ArrayList<Tile[]>();
        Collections.addAll(answer, tiles);
        answer.add(null);

        when(board.nextLine()).thenAnswer(AdditionalAnswers.returnsElementsOf(answer));
        return board;
    }

    private Tile makeMockTile(String visibleValue) {
        Tile X = mock(Tile.class);
        when(X.getVisibleValue()).thenReturn(visibleValue);
        return X;
    }

    @Test
    public void when_board_has_double_figured_max_then_columns_are_double_spaced(){

        // Arrange
        Tile[] tiles = new Tile[] {
                makeMockTile("12"),
                makeMockTile(" "),
                makeMockTile("x")
        };
        Board board = makeMockBoard(2, tiles);
        ConsoleView sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out).println("|12|  | x|");
    }

    @Test
    public void when_board_has_triple_figured_max_then_columns_are_triple_spaced(){

        // Arrange
        Tile[] tiles = new Tile[] {
                makeMockTile("123"),
                makeMockTile(" "),
                makeMockTile("x")
        };
        Board board = makeMockBoard(3, tiles);
        ConsoleView sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out).println("|123|   |  x|");
    }

    private Board makeMockBoard(int columnWidth, Tile[] tiles) {
        Board board = mock(Board.class);
        when(board.nextLine()).thenReturn(tiles).thenReturn(null);
        when(board.columnWidth()).thenReturn(columnWidth);
        return board;
    }

}