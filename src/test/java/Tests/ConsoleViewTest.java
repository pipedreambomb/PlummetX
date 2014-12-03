package Tests;

import org.junit.Before;
import org.junit.Test;
import uk.co.georgenixon.PlummetX.Board;
import uk.co.georgenixon.PlummetX.ConsoleView;
import uk.co.georgenixon.PlummetX.Tile;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class ConsoleViewTest {

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
        Tile tile = Tile.createOpaque(1);
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

    @Test
    public void checkerboard() throws Exception {

        //Arrange
        Board board = mock(Board.class);
        Tile x = mock(Tile.class);
        when(x.getVisibleValue()).thenReturn('X');
        Tile o = mock(Tile.class);
        when(o.getVisibleValue()).thenReturn('O');
        Tile[][] boardData = new Tile[][] { {x, o, x}, {o, x, o}, {x, o, x}};
        when(board.nextLine())
                .thenReturn(boardData[0])
                .thenReturn(boardData[1])
                .thenReturn(boardData[2])
                .thenReturn(null);

        sut = new ConsoleView(out, board);

        // Act
        sut.render();

        // Assert
        verify(out, times(2)).println("|X|O|X|");
        verify(out).println("|O|X|O|");
    }
}