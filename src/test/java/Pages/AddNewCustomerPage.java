package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddNewCustomerPage {

    public String customerID;

    public AddNewCustomerPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@name='name']")
    public WebElement customerNameBox;

    @FindBy(xpath = "//input[@value='m']")
    public WebElement genderMaleBox;

    @FindBy(xpath = "//input[@value='f']")
    public WebElement genderFemaleBox;

    @FindBy(id = "dob")
    public WebElement dobBox;

    @FindBy(xpath = "//textarea[@name='addr']")
    public WebElement addressBox;

    @FindBy(xpath = "//input[@name='city']")
    public WebElement cityBox;

    @FindBy(xpath = "//input[@name='state']")
    public WebElement stateBox;

    @FindBy(xpath = "//input[@name='pinno']")
    public WebElement pinBox;

    @FindBy(xpath = "//input[@name='telephoneno']")
    public WebElement phoneNumberBox;

    @FindBy(xpath = "//input[@name='emailid']")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//input[@name='sub']")
    public WebElement submitButton;

    @FindBy(xpath = "//input[@name='res']")
    public WebElement resetButton;

    @FindBy(xpath = "//input[@type='submit']")
    public  WebElement submitButtonNewDetail;

    @FindBy(xpath = "//p[@class='heading3']")
    public WebElement approvingText;

    @FindBy(xpath = "//table//tr//tr//td[2]")
    public List<WebElement> customerDetails;


    public void addNewCustomer(String name, String gender, String DOB , String address,String City,
                               String state ,String pinCode, String phoneNumber, String emailAddress, String pass){
        this.customerNameBox.sendKeys(name);

        if(gender.equals("male")){
            this.genderMaleBox.click();
        }else {
            this.genderFemaleBox.click();
        }

        this.dobBox.sendKeys(DOB);
        this.addressBox.sendKeys(address);
        this.cityBox.sendKeys(City);
        this.stateBox.sendKeys(state);
        this.pinBox.sendKeys(pinCode);
        this.phoneNumberBox.sendKeys(phoneNumber);
        this.emailBox.sendKeys(emailAddress);
        this.passwordBox.sendKeys(pass);
    }

    public List<String> getActualDetails(){
        List<String> details = new ArrayList<>();
        for (int i=1; i<this.customerDetails.size();i++){
            details.add(this.customerDetails.get(i).getText().trim());
        }
        return details;
    }

    public List<String> getExpectedDetails(String name, String gender, String DOB , String address,String City,
                                         String state ,String pinCode, String phoneNumber, String emailAddress){

        // converting format DOB
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(DOB,formatter);
        DateTimeFormatter formatter1 =DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String expectedDOB= date.format(formatter1);

        List<String> details = new ArrayList<>();
        details.add(name);
        details.add(gender);
        details.add(expectedDOB);
        details.add(address);
        details.add(City);
        details.add(state);
        details.add(pinCode);
        details.add(phoneNumber);
        details.add(emailAddress);

        return details;
    }

    public String getCustomerID() {
        return customerID = this.customerDetails.get(0).getText();
    }

    public List<String> getChangedDetailsTC4(){
        List<String> details = new ArrayList<>();
        for (int i=4; i<this.customerDetails.size();i++){
            details.add(this.customerDetails.get(i).getText().trim());
        }
        return details;
    }

    public List<String> getExpectedDetailsTC4(String address,String City,
                                           String state ,String pinCode, String phoneNumber, String emailAddress){

        List<String> details = new ArrayList<>();
        details.add(address);
        details.add(City);
        details.add(state);
        details.add(pinCode);
        details.add(phoneNumber);
        details.add(emailAddress);

        return details;
    }

    public void changeDetails(String address,String City,
                              String state ,String pinCode, String phoneNumber, String emailAddress){

        this.addressBox.clear();
        this.addressBox.sendKeys(address);
        this.cityBox.clear();
        this.cityBox.sendKeys(City);
        this.stateBox.clear();
        this.stateBox.sendKeys(state);
        this.pinBox.clear();
        this.pinBox.sendKeys(pinCode);
        this.phoneNumberBox.clear();
        this.phoneNumberBox.sendKeys(phoneNumber);
        this.emailBox.clear();
        this.emailBox.sendKeys(emailAddress);
    }

}
