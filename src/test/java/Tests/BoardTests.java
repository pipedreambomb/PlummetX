package Tests;

import org.junit.Test;
import uk.co.georgenixon.PlummetX.Board;
import uk.co.georgenixon.PlummetX.Tile;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTests {

    @Test
    public void empty_board_returns_null_for_next_line() throws Exception {
        Board sut = new Board(0, 0, 7);
        assertThat(sut.nextLine()).isNull();
    }

    @Test
    public void two_by_two_board_returns_lines_then_null() {
        Board sut = new Board(2, 2, 7);
        ArrayList<Tile[]> result = new ArrayList<Tile[]>();
        do {
            result.add(sut.nextLine());
        }
        while (!result.contains(null));

        Tile blank = Tile.createBlank();
        assertThat(result.toArray()).containsExactly(new Tile[][]{
                {blank, blank},
                {blank, blank},
                null
        });
    }

    @Test
    public void when_max_tile_value_is_9_then_column_width_is_1() throws Exception {
        Board sut = makeSut(9);
        assertThat(sut.columnWidth()).isEqualTo(1);
    }

    @Test
    public void when_max_tile_value_is_3_then_column_width_is_1() throws Exception {
        Board sut = makeSut(3);
        assertThat(sut.columnWidth()).isEqualTo(1);
    }

    @Test
    public void when_max_tile_value_is_22_then_column_width_is_2() throws Exception {
        Board sut = makeSut(22);
        assertThat(sut.columnWidth()).isEqualTo(2);
    }

    @Test
    public void when_max_tile_value_is_333_then_column_width_is_3() throws Exception {
        Board sut = makeSut(333);
        assertThat(sut.columnWidth()).isEqualTo(3);
    }

    private Board makeSut(int maxTileValue) {
        return new Board(7, 7, maxTileValue);
    }
}