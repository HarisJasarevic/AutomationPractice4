package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends BasePage {
    private By myAccountNavBar = By.cssSelector("a[title='My Account']");
    private By navbarLoginOption = By.xpath("//a[text()='Login']");
    private By loginEmailField= By.cssSelector("#input-email");
    private By loginPasswordField= By.cssSelector("#input-password");
    private By loginButton = By.cssSelector("input[type='submit']");
    private By labelMyAccount = By.xpath("//h2[text()='My Account']");

    public String expectedLabelText = "My Account";

    private By usernameField = By.cssSelector("input[name='username']");
    private By passwordField = By.cssSelector("input[name='password']");
    private By loginBtn = By.className("radius");

    private By errorMessage = By.cssSelector(".flash.error");

    public Login(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage(){
        clickOnElement(myAccountNavBar);
        clickOnElement(navbarLoginOption);
    }

//    public boolean loginUser(String username, String password){
//        goToLoginPage();
//        typeIn(loginEmailField, username);
//        typeIn(loginPasswordField, password);
//        clickOnElement(loginButton);
//        return readElementsContent(labelMyAccount).equals(expectedLabelText);
//    }

    public void loginUserHeroku(String username, String password) {
        typeIn(usernameField, username);
        typeIn(passwordField, password);
        clickOnElement(loginBtn);
    }

    public boolean isErrorMessagePresent() {
        String actual[] = getTextFromElement(errorMessage).split("(?<=!)");
        String actualText = actual[0];
        String actualMessage = "Your username is invalid!";
        return actualText.equals(actualMessage);
    }





}//end class
