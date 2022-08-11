import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Test
    @Disabled
    @Timeout(value = 22)
    public void MainTimeoutTest() throws Exception {
        Main.main(null);
    }
}
