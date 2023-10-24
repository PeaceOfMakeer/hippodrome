import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    private static final String cannotBeNull = "Horses cannot be null.";
    private static final String cannotBeEmpty = "Horses cannot be empty.";
    private static final String name = "Cartoon";
    private static final double speed = 1.0;
    private static final double distance = 0.0;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testConstructorNotNull(){
        try{
            assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        } catch (IllegalArgumentException e){
            assertEquals(cannotBeNull, e.getMessage());
        }
    }

    @Test
    public void testConstructorNotEmpty(){
        List<Horse> arrayList = new ArrayList<>();
        try{
            assertThrows(IllegalArgumentException.class, () -> new Hippodrome(arrayList));
        }catch(IllegalArgumentException e){
            assertEquals(cannotBeEmpty, e.getMessage());
        }
    }

    @Test
    public void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(name + i, speed, distance));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> horseList = hippodrome.getHorses();
        assertEquals(horses, horseList);
    }

    @Test
    void move() {
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horses = Mockito.mock(Horse.class);
            horsesList.add(horses);
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        hippodrome.move();
        for (Horse horse : horsesList) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Cartoon", 5.0, 10.0));
        horses.add(new Horse("WhiteHorse", 10.0, 110.0));
        horses.add(new Horse("BlackHorse", 11.0, 100.0));
        horses.add(new Horse("LoveHorse", 3.0, 50.0));
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertSame(horses.get(1), winner);
    }
}