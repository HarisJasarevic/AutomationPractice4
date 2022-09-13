package tests;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SeleniumActionsTest extends BaseTests {


    @Test
    public void iFrameTest() {
        driver.get("https://demoqa.com/frames");
        driver.switchTo().frame("frame1");
        String text = driver.findElement(By.id("sampleHeading")).getText();
        System.out.println(text);
    }

    @Test
    public void sliderTest() {
        driver.get("https://demoqa.com/slider");
        WebElement slider = driver.findElement(By.cssSelector(".range-slider"));
        for (int i = 0; i < 100; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        Utils.waitForSeconds(3);
    }

    @Test
    public void tabsTest() {
        driver.get("https://demoqa.com/browser-windows");
        String currentTab = driver.getWindowHandle();
        System.out.println(currentTab);
        WebElement clickNewTab = driver.findElement(By.id("tabButton"));
        clickNewTab.click();
        Utils.waitForSeconds(2);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String text = driver.findElement(By.id("sampleHeading")).getText();
        System.out.println(text);
        driver.close();
        Utils.waitForSeconds(2);
    }


}//end class
