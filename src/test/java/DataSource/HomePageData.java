package DataSource;

import org.testng.annotations.DataProvider;

public class HomePageData {

    @DataProvider(name = "nameCustomers")
    public Object[][] getCustomerInformation(){
        return new Object[][]{
                {"Cristiano Ronaldo","male","02/25/1985","Juventus",
                "Turin","Italy","101555","777333444","ronal@cris.com","Champion"} ,

                {"Madonna","female","08/16/1958","30000 Pacific Coast Hwy","Malibu", "CA","102555",
                "290999111","mad@onna.com","Madame X"},

                {"Quentin Tarantino","male","03/27/1963","2145 BroadStone Ave","Knoxville", "Tennessee","103555",
                "349555888","tar@rmail.com","Django Unchained"}
        };

    }

}