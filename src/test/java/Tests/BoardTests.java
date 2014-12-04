package Tests;

import org.junit.Ignore;
import org.junit.Test;
import uk.co.georgenixon.PlummetX.Board;
import uk.co.georgenixon.PlummetX.ColumnFullException;
import uk.co.georgenixon.PlummetX.Tile;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTests {

    @Test
    public void empty_board_returns_null_for_next_line() throws Exception {
        Board sut = Board.createBlank(0, 0, 7);
        assertThat(sut.nextLine()).isNull();
    }

    @Test
    public void two_by_two_board_returns_lines_then_null() {
        Board sut = Board.createBlank(2, 2, 7);
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

    @Test
    @Ignore
    public void createHardcore_makes_at_least_5_non_blank_tiles() {
//        Board sut = Board.createHardcore(7, 7, 7);
//        int nonBlankCount = 0;
//        Tile[] tileRow;
//        while((tileRow = sut.nextLine()) != null) {
//            for(Tile tile : tileRow) {
//                nonBlankCount = tile.equals(Tile.BlankTile) ? nonBlankCount : nonBlankCount + 1;
//            }
//        }
//        assertThat(nonBlankCount).isGreaterThanOrEqualTo(5);
    }

    @Test
    public void addTile_works_when_tile_is_blank() throws Exception {
        Board sut = Board.createBlank(1, 1, 1);
        Tile insertedTile = Tile.createOpaque(1);
        sut.addTile(0, insertedTile);
        Tile result = sut.nextLine()[0];
        assertThat(result).isEqualTo(insertedTile);
    }

    @Test
    public void addTile_throws_error_when_top_of_column_is_not_blank() throws Exception {

        Board sut = Board.createBlank(1, 1, 1);
        sut.addTile(0, Tile.createOpaque(1));
        try {
            sut.addTile(0, Tile.createOpaque(1));
            fail("ColumnFullException expected because column is full, shouldn't be able to add a tile.");
        } catch (ColumnFullException e) {
            assertThat(e.getMessage()).isEqualTo("Column 0 is full.");
        }
    }

    @Test
    public void addTile_throws_error_when_value_is_over_limit() throws Exception {

        Board sut = Board.createBlank(1, 1, 1);
        try {
            sut.addTile(0, Tile.createOpaque(100000));
            fail("IllegalArgument expected because tile value is higher than board's max value.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo(
                    "Max tile value on this board is 1. This tile's value is 100000.");
        }
    }

    private Board makeSut(int maxTileValue) {
        return Board.createBlank(7, 7, maxTileValue);
    }
}