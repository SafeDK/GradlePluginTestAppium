package tests;

import static org.testng.AssertJUnit.assertTrue;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AndroidLogcatListenerTest extends BaseTest {

    @Test
    public void verifyLogcatListenerCanBeAssigned() {
        final Semaphore messageSemaphore = new Semaphore(1);
        final Duration timeout = Duration.ofSeconds(15);

        driver.addLogcatMessagesListener((msg) -> messageSemaphore.release());
        driver.addLogcatConnectionListener(() -> System.out.println("Connected to the web socket"));
        driver.addLogcatDisconnectionListener(() -> System.out.println("Disconnected from the web socket"));
        driver.addLogcatErrorsListener(Throwable::printStackTrace);
        try {
            driver.startLogcatBroadcast();
            messageSemaphore.acquire();
            // This is needed for pushing some internal log messages
            driver.runAppInBackground(Duration.ofSeconds(1));
            assertTrue(String.format("Didn't receive any log message after %s timeout",
                            DurationFormatUtils.formatDuration(timeout.toMillis(), "H:mm:ss", true)),
                    messageSemaphore.tryAcquire(timeout.toMillis(), TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            messageSemaphore.release();
            driver.stopLogcatBroadcast();
        }
    }
}
