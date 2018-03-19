package i9.defence.platform.socket.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MessageIndexTest {

    @Test
    public void testIndex() {
        AtomicInteger index = new AtomicInteger(1);
        for (int i = 0; i < 10; i ++) {
            System.out.println(index.getAndIncrement());
        }
    }
}
