package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.Hook;

import static util.Tools.waitBy;

public class LoginPage {
    //element
    @FindBy(how = How.ID, using = "user")
    private WebElement userInput;
    @FindBy(how = How.ID, using = "password")
    private WebElement passwordInput;
    @FindBy(how = How.ID, using = "login")
    private WebElement loginButton;
    @FindBy(how = How.XPATH, using = "//a[@aria-label='Página Inicial do Trello']")
    private WebElement initialPage;

    //contructor
    public LoginPage() {
        PageFactory.initElements(Hook.getDriver(), this);
    }

    public LoginPage access(){
        Hook.getDriver().get("https://trello.com/login");
        return this;
    }

    public LoginPage doLogin(String user, String pass){
        waitBy(userInput).sendKeys(user);
        waitBy(passwordInput).sendKeys(pass);
        waitBy(loginButton).click();
        return this;
    }
    public String checkInitialPage(){
        return waitBy(initialPage).getAttribute("aria-label");
    }

}
