package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href='http://demo.guru99.com/']")
    public WebElement clickHere;

    @FindBy(name = "emailid")
    public WebElement sendEmail;

    @FindBy(name = "btnLogin")
    public WebElement submit;

    @FindBy(xpath = "(//td[@align='center'])[3]")
    public WebElement userID;

    @FindBy(xpath = "(//td[@align='center'])[5]")
    public WebElement password;

    @FindBy(xpath = "//input[@name='uid']")
    public WebElement sendUserID;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement sendPassword;



    public void login(WebDriver driver){
        driver.manage().deleteAllCookies();
        driver.navigate().to("http://demo.guru99.com/V4/");

        this.clickHere.click();
        this.sendEmail.sendKeys("vvv@gmail.com");
        this.submit.submit();
        String userID = this.userID.getText().trim();
        String password = this.password.getText().trim();

        driver.navigate().back();
        driver.navigate().back();
        this.sendUserID.sendKeys(userID);
        this.sendPassword.sendKeys(password);
        this.submit.click();
    }
}
