
package Utils;

        import org.openqa.selenium.WebDriver;

        import java.util.Set;

public class BrowserUtils {


    public static void switchWindowByTitle(WebDriver driver, String targetTitle){
        Set<String> ids=driver.getWindowHandles();
        for(String id: ids){
            if(!driver.getTitle().equals(targetTitle)){
                driver.switchTo().window(id);
            }
        }
    }
    public static void switchWindowByUrl(WebDriver driver, String url){
        Set<String> ids=driver.getWindowHandles();
        for(String id:ids){
            if(!driver.getCurrentUrl().contains(url)){
                driver.switchTo().window(id);
            }
        }
    }
    public static void closeWindows(WebDriver driver, String parentId){
        Set<String> ids=driver.getWindowHandles();
        for(String id:ids){
            if(!id.equals(parentId)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }

}

