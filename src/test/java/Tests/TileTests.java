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

        assertThat(sut.getVisibleValue()).isEqualTo('X');
    }

    @Test
    public void Returns_x_when_partially_opaque(){

        Tile sut = Tile.createPartiallyOpaque(tileValue);

        assertThat(sut.getVisibleValue()).isEqualTo('x');
    }

    @Test
    public void Returns_value_when_not_opaque(){

        Tile sut = Tile.createClear(tileValue);

        assertThat(sut.getVisibleValue()).isEqualTo(tileValue.toString().charAt(0));
    }

//    @Test
//    public void Reveals_opaque_to_partial(){}
//
//    @Test
//    public void Reveals_partial_to_clear(){}
//
//    @Test
//    public void Reveals_opaque_to_partial_to_clear(){}
}