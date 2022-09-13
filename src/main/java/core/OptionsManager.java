package core;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class OptionsManager {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        //options.addArguments("headless"); //testiranje bez otvaranja browsera, u pozadini
        //options.addArguments("window-size=1920,1080");
        options.addArguments("--disable-popup-blocking");
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        //options.addArguments("headless"); //testiranje bez otvaranja browsera, u pozadini
        //options.addArguments("window-size=1920,1080");
        options.addArguments("--disable-popup-blocking");
        return options;
    }







}//end class
