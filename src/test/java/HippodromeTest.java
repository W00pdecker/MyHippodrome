import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class HippodromeTest {



    @Test
    public void HippodromeConstructorNullTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    public void HippodromeConstructorEmptyTest() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(horses);
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());

    }

    @Test
    public void HippodromeGetHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            horses.add(new Horse("horse" + i, i));
        }
        Hippodrome drome = new Hippodrome(horses);
        assertEquals(horses, drome.getHorses());
    }

    @Test
    public void HippodromeMoveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i =0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome drome = new Hippodrome(horses);
        drome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void HippodromeGetWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            horses.add(new Horse("horse" + i, i, i));
        }
        Hippodrome drome = new Hippodrome(horses);

        assertEquals(9, drome.getWinner().getDistance());
    }


}
