package pages;

import model.RegisterUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register extends BasePage {
    private By myAccountNavBar = By.cssSelector("a[title='My Account']");
    private By navbarRegisterOption = By.xpath("//a[text()='Register']");
    private By firstNameField = By.cssSelector("#input-firstname");
    private By lastNameField = By.cssSelector("#input-lastname");
    private By emailField = By.cssSelector("#input-email");
    private By telephoneField = By.cssSelector("#input-telephone");
    private By passwordField = By.cssSelector("#input-password");
    private By confirmPasswordField = By.cssSelector("#input-confirm");
    private By privacyPolicyCheckbox = By.cssSelector("input[name='agree']");
    private By continueButton = By.cssSelector("input[type='submit']");
    private By successMessage = By.xpath("//div[@id='content']/h1");
    private By logoutButton = By.xpath("//div[@class='list-group']//a[text()='Logout']");


    public Register(WebDriver driver) {
        super(driver);
    }

    public Register goToRegisterPage(){
        clickOnElement(myAccountNavBar);
        clickOnElement(navbarRegisterOption);
        return this;
    }

    public Register registerUser(){
        RegisterUser user = new RegisterUser();
        typeIn(firstNameField, user.getFirstName());
        typeIn(lastNameField, user.getLastName());
        typeIn(emailField, user.getEmail());
        typeIn(telephoneField, user.getPhone());
        typeIn(passwordField, user.getPassword());
        typeIn(confirmPasswordField, user.getPassword());
        clickOnElement(privacyPolicyCheckbox);
        clickOnElement(continueButton);
        //takeScreenshot(driver);
        return this;
    }
    public boolean isUserRegistered() {
        String expectedSuccessText = "Your Account Has Been Created!";
        return matchesExpectedText(successMessage, expectedSuccessText);
    }

    public boolean isLogoutButtonPresent() {
        return isElementDisplayed(logoutButton);
    }



}//end class
