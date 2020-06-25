package Tests.comV4;

import DataSource.HomePageData;
import Pages.AddNewCustomerPage;
import Pages.HomePage;
import Pages.LoginPage;
import Tests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.sql.DataSource;
import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LoginTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    AddNewCustomerPage addNewCustomerPage;

    @BeforeClass
    public void setupPage() {

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        addNewCustomerPage = new AddNewCustomerPage(driver);
    }

    @Test(priority = 1 ,enabled = true)
    public void testCase1() {
        softAssert = new SoftAssert();

        loginPage.login(driver);

        //Validate Functionality names and Validate size of the functionality is 15
        //Validate all functionality names are starting with upper case(first and second words)
        int numberOfLink = 0;

        for (int i = 0; i < homePage.actualNameOfLink.size(); i++) {
            String actualName = homePage.actualNameOfLink.get(i).getText().trim();
            String expectedName = homePage.expectedNameOfLink().get(i);
            int indexOfSpace = actualName.indexOf(" ") + 1;

            softAssert.assertEquals(actualName, expectedName);

            softAssert.assertTrue(actualName.charAt(0) >= 65 && actualName.charAt(0) <= 90);

            if(actualName.contains(" ")){
            softAssert.assertTrue(actualName.charAt(indexOfSpace) >= 65 && actualName.charAt(indexOfSpace) <= 90,
                    actualName+"--> in this link second word beginning with lowerCase character.");
            }
            numberOfLink++;
        }
        softAssert.assertEquals(numberOfLink, 15);

        softAssert.assertAll();
    }

    @Parameters({"customerName","gender","DOB","address","City","State","zipCode","mobileNumber","emailAddress","password"})
    @Test(priority = 2 ,enabled = true)
    public void testCase2(String name, String gender, String DOB , String address,String City,
                      String state ,String pinCode, String phoneNumber, String emailAddress, String pass){
        softAssert = new SoftAssert();

        loginPage.login(driver);
        //Click New Customer button
        homePage.newCustomer.click();
        //Validate title is "Add New Customer"
        String expectedText = "Add New Customer";
        String actualText = homePage.addNewCustomerText.getText().trim();

        Assert.assertEquals(actualText,expectedText);

        addNewCustomerPage.addNewCustomer(name,gender,DOB,address,City,state,pinCode,phoneNumber,emailAddress,pass);
        addNewCustomerPage.submitButton.click();

        //Validate "Customer Registered Successfully!!!" text message
        String expextedApprovingText ="Customer Registered Successfully!!!";
        String actualApprovingText = addNewCustomerPage.approvingText.getText().trim();
        Assert.assertEquals(actualApprovingText,expextedApprovingText);

        //Validate Customer Details are matching with provided values(Like Gender, Name, Phone number etc)
        List<String> actualDetails=addNewCustomerPage.getActualDetails();
        List<String> expectedDetails=addNewCustomerPage.getExpectedDetails(name,gender,DOB,address,City,
                state,pinCode,phoneNumber,emailAddress);

        for (int i=0; i<actualDetails.size();i++){
            softAssert.assertEquals(actualDetails.get(i),expectedDetails.get(i));
        }

        //storing customer ID for TC4
        addNewCustomerPage.getCustomerID();

        softAssert.assertAll();
    }

    @Test(priority = 3 ,enabled = true ,dataProvider = "nameCustomers" , dataProviderClass = HomePageData.class)
    public void testCase3(String name, String gender, String DOB , String address,String City,
                          String state ,String pinCode, String phoneNumber, String emailAddress, String pass) throws InterruptedException {

        //Create 4 new customer with different Customer Information
        //Use Data Provider to get the data and create data provider inside another class
        softAssert = new SoftAssert();

        homePage.continueButton.click();
        Thread.sleep(1500);
        homePage.newCustomer.click();
        //adding new Customers
        addNewCustomerPage.addNewCustomer(name,gender,DOB,address,City,state,pinCode,phoneNumber,emailAddress,pass);
        addNewCustomerPage.submitButton.click();

        //Validate the customer information is matching once you click submit button for each new customer
        List<String> actualCuctomerDetails = addNewCustomerPage.getActualDetails();
        List<String> expectedCustomerDetails = addNewCustomerPage.getExpectedDetails(name,gender,DOB,address,
                City,state,pinCode,phoneNumber,emailAddress);

        for(int i=0; i<actualCuctomerDetails.size();i++){

        softAssert.assertEquals(actualCuctomerDetails,expectedCustomerDetails);
        }

        softAssert.assertAll();
    }

    @Parameters({"newAddress","newCity","newState","newZipCode","newMobileNumber","emailAddressMichael"})
    @Test(priority = 4, enabled = true)
    public void testCase4(String address,String City,
                          String state ,String pinCode, String phoneNumber, String emailAddress) throws InterruptedException {

        softAssert = new SoftAssert();

        //Click "edit customer" button
        homePage.editCustomerButton.click();

        //Click provide the CustomerId for "Michael Green"
        homePage.customerIDbox.sendKeys(addNewCustomerPage.customerID);
        System.out.println(addNewCustomerPage.customerID);
        Thread.sleep(1000);
        addNewCustomerPage.submitButtonNewDetail.click();

        //Change address "227 W Monroe"
        //Change city "Chicago" to "Des Plaines"
        //Change PIN the 443322
        //Change email address "michaelqueen@gmail.com"
        //Change phone number to 2243084345
        addNewCustomerPage.changeDetails(address,City,state,pinCode,phoneNumber,emailAddress);
        //Click submit button
        addNewCustomerPage.submitButton.click();

       //Validate Address, City, PIN, Email and Phone Number
        List<String> ectualDetailsTC4 = addNewCustomerPage.getChangedDetailsTC4();
        List<String> expectedDetailsTC4 = addNewCustomerPage.getExpectedDetailsTC4(address,City,
                state,pinCode,phoneNumber,emailAddress);

        for(int i = 0; i<ectualDetailsTC4.size(); i++){

            softAssert.assertEquals(ectualDetailsTC4 , expectedDetailsTC4);
        }

        softAssert.assertAll();
    }


    @Test
    public void test99(){
        System.out.println("ves zbs");
    }
}
