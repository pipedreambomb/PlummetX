package Tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import uk.co.georgenixon.PlummetX.Tile;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Created by George on 07/09/2014.
 */
@RunWith(Parameterized.class)
public class TileTests {

    private final Integer tileValue;

    public TileTests(int tileValue){
        this.tileValue = tileValue;
    }

    @Parameters
    public static Collection<Integer[]> tileValues(){
        return Arrays.asList(
                new Integer[] {1},
                new Integer[] {5},
                new Integer[] {11});
    }

    @Test
    public void Returns_X_when_fully_opaque(){

        Tile sut = Tile.createOpaque(tileValue);

        assertThat(sut.getVisibleValue()).isEqualTo("X");
    }

    @Test
    public void returns_x_when_partially_opaque(){

        Tile sut = Tile.createPartiallyOpaque(tileValue);

        assertThat(sut.getVisibleValue()).isEqualTo("x");
    }

    @Test
    public void returns_value_when_not_opaque(){

        Tile sut = Tile.createClear(tileValue);

        assertThat(sut.getVisibleValue()).isEqualTo(tileValue.toString());
    }

    @Test
    public void reveals_opaque_to_partial(){

        Tile sut = Tile.createOpaque(tileValue);
        sut.destruct();
        assertThat(sut.getVisibleValue()).isEqualTo("x");
    }

    @Test
    public void reveals_partial_to_clear() {
        Tile sut = Tile.createPartiallyOpaque(tileValue);
        sut.destruct();
        assertThat(sut.getVisibleValue()).isEqualTo(tileValue.toString());
    }

    @Test
    public void reveals_opaque_to_partial_to_clear(){

        // Arrange
        Tile sut = Tile.createOpaque(tileValue);

        // Act
        sut.destruct();
        sut.destruct();

        // Assert
        assertThat(sut.getVisibleValue()).isEqualTo(tileValue.toString());
    }

    @Test
    public void destructs_visible_to_empty(){

        // Arrange
        Tile sut = Tile.createOpaque(tileValue);

        // Act
        sut.destruct();
        sut.destruct();
        sut.destruct();

        // Assert
        assertTileIsDisplayingAsEmpty(sut);
    }

    private void assertTileIsDisplayingAsEmpty(Tile sut) {
        assertThat(sut.getVisibleValue()).isEqualTo(" ");
    }

    @Test
    public void when_tile_is_empty_visible_value_is_space(){
        Tile sut = Tile.createBlank();

        assertTileIsDisplayingAsEmpty(sut);
    }
}
