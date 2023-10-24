import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Nested;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    private final String isNull = "Name cannot be null.";
    private final String blankName = "Name cannot be blank.";
    private final String secondNegativeSpeed = "Speed cannot be negative.";
    private final String thirdNegativeDistance = "Distance cannot be negative.";
    private final String name = "Cartoon";
    private final Double speed = 5.0;
    private final Double distance = 11.0;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void firstParameterNull(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
    }

    @Test
    public void testConstructorWithNullNameMessage() {

        try {
            new Horse(null, speed, distance);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(isNull, e.getMessage());
        }
    }

    @Nested
    class CreatingHorseWithBlankName {
        Throwable exception;

        @BeforeEach
        @ParameterizedTest
        @MethodSource("checkingForEmptyNames")
        public void checkingForEmptyName() {
            exception = assertThrows(IllegalArgumentException.class, () -> new Horse("", speed, distance));
        }

        static Stream<String> checkingForEmptyNames() {
            return Stream.of("", " ", "\t", "\n", "\u00A0", "\u2007", "\u202F", "\u000B", "\f", "\r", "\u001C", "\u001D", "\u001E", "\u001F");
        }

        @Test
        public void checkConstructorFirstParameterEmpty() {
            try {
                new Horse(exception.getClass().getName(), speed, distance);
            } catch (IllegalAccessError e) {
                assertEquals(blankName, e.getMessage());
            }
        }
    }

    @Test
    public void SecondParametersNegativeNumber(){
        assertThrows(IllegalArgumentException.class,() -> new Horse(name, -speed, distance));
    }
    @Test
    public void ExceptionSecondParametersNegativeNumber(){
        try{
            new Horse(name, -speed, distance);
        }catch (IllegalArgumentException e){
            assertEquals(secondNegativeSpeed, e.getMessage());
        }
    }

    @Test
    public void ThirdParametersNegativeNumber(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
    }
    @Test
    public void ExceptionThirdParametersNegativeNumber(){
        try {
            new Horse(name, speed, -distance);
        }catch (IllegalArgumentException e){
            assertEquals(thirdNegativeDistance, e.getMessage());
        }
    }


    @Test
    void getNameConstructor() {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeedConstructor() {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceConstructor() {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    public void distanceParametersConstructor() {
        Horse horse = new Horse(name, speed);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void move() {

        try(MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)){
            new Horse(name, speed, distance).move();
            utilities.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

}