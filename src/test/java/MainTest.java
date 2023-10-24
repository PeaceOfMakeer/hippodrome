import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;


class MainTest {




    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
//    @Disabled
    void main() throws Exception {
        Main.main(new String[0]);
    }

}