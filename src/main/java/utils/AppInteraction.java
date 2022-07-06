package utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppInteraction {

    private AndroidDriver driver;

    public AppInteraction(AndroidDriver driver) {
        this.driver = driver;
    }

    public void closeApp(long waitTime) {

        try {
            System.out.println("close app");
            driver.closeApp();
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void launchApp(long waitTime) {
        try {
            System.out.println("launch app");
            driver.launchApp();
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // uninstall android app  in 3 stages: force stop -> clear app data -> uninstall
    public boolean uninstallApp(String appPackage) {

//        System.out.println("before");
//        ExecuteCommand exe = new ExecuteCommand();
//        System.out.println("after");
//        try {
//            System.out.println("force stop");
//            String forceStop = (Commands.FORCE_STOP + appPackage);
//            exe.execute(forceStop);
//            System.out.println("clear data");
//            String clearData = (Commands.CLEAR_APP_DATA + appPackage);
//            exe.execute(clearData);
//            System.out.println("uninstallApp");
//            String uninstallCommand = (Commands.UNINSTALL_ANDROID_APP + appPackage);
//            exe.execute(uninstallCommand);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("can't uninstall app");
//            return false;
//        }
        return true;
    }
}
