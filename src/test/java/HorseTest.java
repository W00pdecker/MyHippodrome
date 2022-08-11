import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;


public class HorseTest {

    private Horse horse;

    @Test
    public void constructorNullNameTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                    horse = new Horse(null, 0.0, 0.0)
                );
        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    public void constructorBlankNameTest(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                    horse = new Horse(name, 0.0, 1.0)
                );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructorNegativeSpeedTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                    horse = new Horse("horse", -2, 0.0)
                );
        assertEquals("Speed cannot be negative.", exception.getMessage());

    }

    @Test
    public void constructorNegativeDistanceTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                    horse = new Horse("horse", 0.0, -3)
                );
        assertEquals("Distance cannot be negative.", exception.getMessage());

    }

    @Test
    public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
        horse = new Horse("Horse", 1, 3);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        assertEquals("Horse", name.get(horse));
    }

    @Test
    public void getSpeedTest() {
        horse = new Horse("Horse", 1, 3);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        horse = new Horse("Horse", 1, 3);
        assertEquals(3, horse.getDistance());
        horse = new Horse("Horse", 3);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveTest() {
        try (MockedStatic<Horse> dummyHorse = Mockito.mockStatic(Horse.class)) {
            new Horse("dyd", 3, 8).move();
            dummyHorse.verify(times(1), () -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void getRandomDoubleTest() {
        try (MockedStatic<Horse> dummyHorse = Mockito.mockStatic(Horse.class)) {
            dummyHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("qwerty", 20, 5);
            horse.move();
            assertEquals(15, horse.getDistance());
        }
    }
}



