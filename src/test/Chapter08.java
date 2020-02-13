import org.junit.Test;

import java.util.logging.Logger;

public class Chapter08 {
    @Test
    public void firstTest() {
        Logger logger = Logger.getLogger("MyLogger");
        logger.info("hello");
    }
}
