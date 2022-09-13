package tests;

import listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Register;

@Listeners(TestListener.class)
public class RegisterTests extends BaseTests {

    Register register;

    @BeforeMethod(alwaysRun = true)
    public void localSetup() {
        register = new Register(driver);
    }

    @Test
    public void registerUserTest() {
        register.goToRegisterPage()
                .registerUser();
        softAssert.assertTrue(register.isUserRegistered());
        softAssert.assertTrue(register.isLogoutButtonPresent());
        softAssert.assertAll();
    }




}//end class
