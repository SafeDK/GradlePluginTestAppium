package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;





public class LogUtils {

    private AndroidDriver driver;
    private String lastSearchResult;

    public LogUtils(AndroidDriver driver) {
        this.driver = driver;
    }
    // search within the testing device log
    //
    public boolean searchLog(List<String> log, String[][] toSearch){

        int counter = 0;
        System.out.println("search in the log");
        for (String[] stringsToSearch:toSearch){
            for (String line:log){
                if (searchLogLine(line, stringsToSearch)){
                    counter += 1;
                    break;
                }
            }
        }
        if (counter == toSearch.length) {
//            System.out.println("all the desired log entries were found");
            return true;
        }
        else {
//            System.out.println("one or more of the desired log entries weren't found");
            return false;
        }
    }

    // grab the logcat and insert it to List<String>
    public List<String> captureLogcat(){
        List<String> logcat = new ArrayList<String>();
        List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
        for (org.openqa.selenium.logging.LogEntry logEntry:logEntries){
            String line = logEntry.getMessage();
//            System.out.println(line);
            logcat.add(line);
        }
        return logcat;
    }

    public String getLastSearchResult() {
        return lastSearchResult;
    }

    public List<String> getStackTrace(List<String> log, String[] startAt, String[] finishAt){

        List<String> stackTrace = new ArrayList();
        for (String line:log){

            if (searchLogLine(line, startAt)) {
                stackTrace.add(line);
            }

            if (searchLogLine(line, finishAt)) {

                break;
            }
        }
        return stackTrace;
    }


    // search for one line within the testing device log
    private boolean searchLogLine(String line, String[] logEntries) {

        int counter = 0;
        for(String logLine:logEntries){
            if (line.contains(logLine)){
                counter += 1;
                if (counter == logEntries.length){
                    System.out.println("found: " + line);
                    lastSearchResult = line;
                    return true;
                }
            }
        }
        return false;
    }




}
