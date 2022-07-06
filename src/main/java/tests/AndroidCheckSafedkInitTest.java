package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.App;
import utils.LogUtils;

import java.util.List;

public class AndroidCheckSafedkInitTest extends BaseTest {

    @Test
    public void checkSafedkInit() throws InterruptedException {

        appInteraction.launchApp(1000);
        System.out.println("checking initialization and configuration download");
        String[][] init = {{"SafeDKMain", "configurationDownloadCompleted isActive true"}, {"SafeDK", "SafeDK version: 4.5.4"}};
        Assert.assertTrue(logUtils.searchLog(logUtils.captureLogcat(), init), "ERROR - SafeDK wasn't initialized properly");
        Thread.sleep(5000);
        String[][] crash = {{"AndroidRuntime", "Process:", app.getAppPackage()}};
        List<String> logcat = logUtils.captureLogcat();
        boolean isCrash = logUtils.searchLog(logcat, crash);
        if (isCrash) {
            String[] splitItems = logUtils.getLastSearchResult().split("PID: ");
            String pid = splitItems[splitItems.length - 1];
            String[] startAt = {"AndroidRuntime", pid};
            String[] finishAt = {"ActivityManager", "Force finishing activity", app.getAppPackage()};
            System.out.println(logUtils.getStackTrace(logcat, startAt, finishAt));
        }
        Assert.assertFalse(isCrash, "ERROR - App crashed");

    }
}
