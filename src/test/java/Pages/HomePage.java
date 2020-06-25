package Pages;

import DataSource.HomePageData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='menusubnav']//li")
    public List<WebElement> actualNameOfLink;

    @FindBy(xpath = "//a[@href='addcustomerpage.php']")
    public WebElement newCustomer;

    @FindBy(xpath = "//p[@class='heading3']")
    public WebElement addNewCustomerText;

    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    public  WebElement continueButton;

    @FindBy(xpath = "//a[@href='EditCustomer.php']")
    public WebElement editCustomerButton;

    @FindBy(name = "cusid")
    public WebElement customerIDbox;



    public List<String> expectedNameOfLink() {
        List<String> namesOfLink = new ArrayList<>();
        namesOfLink.add("Manager");
        namesOfLink.add("New Customer");
        namesOfLink.add("Edit Customer");
        namesOfLink.add("Delete Customer");
        namesOfLink.add("New Account");
        namesOfLink.add("Edit Account");
        namesOfLink.add("Delete Account");
        namesOfLink.add("Deposit");
        namesOfLink.add("Withdrawal");
        namesOfLink.add("Fund Transfer");
        namesOfLink.add("Change Password");
        namesOfLink.add("Balance Enquiry");
        namesOfLink.add("Mini Statement");
        namesOfLink.add("Customised Statement");
        namesOfLink.add("Log out");
        return namesOfLink;
    }


}
