package core;

import org.openqa.selenium.WebDriver;
import utils.Utils;

import static utils.Utils.dotEnv;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Environment {

    private String homeUrl;
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Environment.class.getName());

    public Environment(WebDriver driver) {
        this.driver = driver;
    }

    public void openBrowser() {
        String environment = dotEnv().get("ENV");
        if (environment.equalsIgnoreCase("DEV")) {
            homeUrl = "https://demo.opencart.com/";
        }else if ((dotEnv().get("ENV").equalsIgnoreCase("QA"))) {
            homeUrl = "https://the-internet.herokuapp.com/login";
        }
        logger.info("Opening browser :" + dotEnv().get("BROWSER") + " And navigating to: " + homeUrl);
        driver.get(homeUrl);
    }



}//end class
