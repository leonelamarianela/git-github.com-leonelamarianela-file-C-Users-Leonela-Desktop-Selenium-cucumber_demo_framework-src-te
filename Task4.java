package framework;


        import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.Select;

        import java.text.SimpleDateFormat;
        import java.util.*;

/**
 * Created by Leonela on 3/13/2018.
 */
/*given url https://www.hotels.com/
when select hotels deal
then i select destination
then select checkin and add 6 nights for check out date
then select number of room
then select amount adults from dropdowm menu
then select how many kids and ages from dropdown manu
and click on search button
 */
public class Task4 {

    public static void main(String arg[]) {


        System.setProperty("webdriver.chrome.driver", "C:/Users/Leonela/Desktop/Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com/");

        String textToSelect = "Minnesota City";

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1); // to get tomorrows day
        Date day = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        String tomorrow = sdf.format(day.getTime());
        Calendar chkout = Calendar.getInstance();
        chkout.add(Calendar.DAY_OF_YEAR, 7);
        Date plus6days = chkout.getTime();
        String sixNights = sdf.format(plus6days.getTime());


       // driver.findElement(By.xpath("//*[@id=\"managed-overlay\"]/button")).click();
        driver.findElement(By.id("hdr-deals")).click();

        //    select city from suggestion list
        driver.findElement(By.xpath("//*[@id=\"qf-1q-destination\"]")).sendKeys("Minnesota City");

        List<WebElement> elements = driver.findElements(By.className("autosuggest-category-result"));
            for (WebElement s1 : elements) {
               while(s1.getText().contains(textToSelect)) {
                    s1.click();
                }
            }

        driver.findElement(By.id("qf-1q-localised-check-in")).click();
        //WebElement checkIn = driver.findElement(By.id("widget-query-label-2"));
        WebElement dateWidget = driver.findElement(By.className("widget-daterange-cont"));
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        for (WebElement cell : columns) {
            String days = cell.getText();
            //Select tomorrow Date
           if (days.equals(tomorrow)) {
               cell.click();
            }
        }
        // select check out date
        driver.findElement(By.id("widget-query-label-3")).click();
        WebElement dateWidget1 = driver.findElement(By.className("widget-datepicker-bd"));
        List<WebElement> checkout = dateWidget.findElements(By.tagName("td"));

        for (WebElement dayOut : checkout) {
            String checkOutDate = dayOut.getText();
            //Select tomorrow Date
            if (checkOutDate.equals(sixNights)) {
                dayOut.click();
            }
        }

            //select number of rooms
            Select selectrooms = new Select(driver.findElement(By.className("query-rooms")));
            selectrooms.selectByIndex(0);
            //select number of adults
            Select selectadults = new Select(driver.findElement(By.id("qf-1q-room-0-adults")));
            selectadults.selectByIndex(1);
            //select number of childrens
            Select selectchildrens = new Select(driver.findElement(By.id("qf-1q-room-0-children")));
            selectchildrens.selectByIndex(2);
            //select age for kid # 1
            Select kidAge1 = new Select(driver.findElement(By.id("qf-1q-room-0-child-0-age")));
            kidAge1.selectByIndex(3);
            //select age for kid # 2
            Select kidAge2 = new Select(driver.findElement(By.id("qf-1q-room-0-child-1-age")));
            kidAge2.selectByIndex(5);
            // search
            driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Search')]")).click();



    }
}
