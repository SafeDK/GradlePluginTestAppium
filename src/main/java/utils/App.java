package utils;

public class App {

    private String apk;
    private String appPackage;
    private String appActivity;

    public App() {

        this.apk = "app-mergemiracle-release.apk";
        this.appPackage = "com.zymobile.merge.miracle";
        this.appActivity = "com.example.max.MainActivity";

    }

    public String getApk() {
        return apk;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }
}
