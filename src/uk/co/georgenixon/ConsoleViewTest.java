package uk.co.georgenixon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class ConsoleViewTest {

    private ConsoleView sut;
    private PrintStream out;

    @Before
    public void setUp() throws Exception {
        out = mock(PrintStream.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void two_by_one_Xs() throws Exception {

        // Arrange
        Board board = mock(Board.class);
        Tile tile = mock(Tile.class);
        when(tile.getVisibleValue()).thenReturn('X');
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
        Board board = mock(Board.class);
        Tile tile = mock(Tile.class);
        when(tile.getVisibleValue()).thenReturn('X');
        Tile[] tiles = new Tile[] { tile};
        when(board.nextLine()).thenReturn(tiles).thenReturn(null);
        sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out).println("|X|");
    }

    @Test
    public void two_by_two_5s() throws Exception {

        // Arrange
        Board board = mock(Board.class);
        Tile tile = mock(Tile.class);
        when(tile.getVisibleValue()).thenReturn('5');
        Tile[] tiles = new Tile[] { tile, tile};
        when(board.nextLine()).thenReturn(tiles).thenReturn(tiles).thenReturn(null);
        sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out, times(2)).println("|5|5|");
    }
}