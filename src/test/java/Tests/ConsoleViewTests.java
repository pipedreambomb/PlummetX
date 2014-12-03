package Tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import uk.co.georgenixon.PlummetX.Board;
import uk.co.georgenixon.PlummetX.ConsoleView;
import uk.co.georgenixon.PlummetX.Tile;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
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

    private Board makeMockBoard(Tile[] tiles) {
        Board board = mock(Board.class);
        when(board.nextLine()).thenReturn(tiles).thenReturn(null);
        return board;
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
        for (Tile[] line : tiles) {
            answer.add(line);
        }
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
    @Ignore
    public void double_figured_tiles(){}


}