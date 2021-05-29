package pageObjects;

import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserSetup {
    public WebDriver setup() {
        try {
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
            Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
            Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Capabilities caps = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(caps);
        caps = driver.getCapabilities();
        driver.close();
        driver = null;
        Map<String, String> a = (Map<String, String>) caps.getCapability("goog:chromeOptions");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", a.get("debuggerAddress"));
        String commandCMD = "chrome.exe -remote-debugging-port=" + a.get("debuggerAddress").substring(10) + " -user-data-dir=C:\\chromeData";
        String command = "cmd /c start cmd.exe  /K \"cd c:/Program Files/Google/Chrome/Application &&" + commandCMD;
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kill cmd");
        try {
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("GET the browser");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ChromeDriver(options);

    }
}
