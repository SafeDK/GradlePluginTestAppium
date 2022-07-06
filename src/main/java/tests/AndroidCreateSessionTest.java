package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.LogUtils;

import java.io.File;
import java.util.Set;

public class AndroidCreateSessionTest extends BaseTest{

//    @BeforeClass
//    @Override
//    public void setUp() throws Exception {
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        File appDir = new File(classpathRoot, "src/main/apps");
//        File app = new File(appDir.getCanonicalPath(), "app-numberzilla-release.apk");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "Android Device");
//        capabilities.setCapability("app", app.getAbsolutePath());
//        capabilities.setCapability("appPackage", "com.appcraft.number.puzzle");
//        capabilities.setCapability("appActivity", "com.example.max.MainActivity");
//        driver = new AndroidDriver(getServiceUrl(), capabilities);
//    }

    @Test()
    public void testCreateSession() throws InterruptedException {
        String activity = driver.currentActivity();
        String pkg = driver.getCurrentPackage();
        Assert.assertEquals(activity, "com.example.max.MainActivity");
        Thread.sleep(10000);
        System.out.println(activity);
        Assert.assertEquals(pkg, "com.zymobile.merge.miracle");
    }

}