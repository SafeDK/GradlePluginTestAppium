package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import utils.App;
import utils.AppInteraction;
import utils.AppiumDesiredCapabilities;
import utils.LogUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {

    private static AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    public AndroidDriver driver;
    public LogUtils logUtils;
    public AppInteraction appInteraction;
    public App app;

//    @BeforeSuite
//    public void globalSetup () throws IOException {
////Build the Appium service
//        builder = new AppiumServiceBuilder();
//        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//        builder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");
//
////Start the server with the builder
//        service = AppiumDriverLocalService.buildService(builder);
//        service.stop();
//        service.start();
//    }

    @AfterSuite
    public void globalTearDown () {
        if (service != null) {
            service.stop();
        }
    }

    public URL getServiceUrl () throws MalformedURLException {
//        System.out.println(service.getUrl());
//        return service.getUrl();
        return new URL("http://0.0.0.0:4723/wd/hub");
    }

    @BeforeClass
    public void setUp() throws Exception {

        setApp();
        initAppiumDriver();
        this.logUtils = new LogUtils(driver);
        this.appInteraction = new AppInteraction(driver);
    }

    public void initAppiumDriver() throws IOException {
        AppiumDesiredCapabilities capabilities = new AppiumDesiredCapabilities();
        driver = new AndroidDriver(getServiceUrl(), capabilities.caps());
    }

    public void setApp() {
        this.app = new App();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}