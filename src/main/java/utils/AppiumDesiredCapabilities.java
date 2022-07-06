package utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class AppiumDesiredCapabilities {


    private String deviceName;
    private String automationName;
    private Boolean autoLaunch;
    private Boolean eventTimings;

    public App app;

    public AppiumDesiredCapabilities() {
        this.app = new App();
        this.deviceName = "Android Device";
        this.automationName = "UiAutomator2";
        this.autoLaunch = false;
        this.eventTimings = true;
    }

    public DesiredCapabilities caps() throws IOException {

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "src/main/apps");
        File apk = new File(appDir.getCanonicalPath(), app.getApk());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("app", apk.getAbsolutePath());
        capabilities.setCapability("appPackage", app.getAppPackage());
        capabilities.setCapability("appActivity", app.getAppActivity());
        capabilities.setCapability("autoLaunch", autoLaunch);
        capabilities.setCapability("eventTimings", eventTimings);

        return capabilities;

    }

}
