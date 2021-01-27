import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {
    private static WebDriver browser;
    public static final int WAIT_FOR_TIME = 2;

    public static void main (String args[]){
        System.out.println("Selenium");

        setUp();

        searchByKeyword("Baranauskas");

        compareResults();

        close();
    }

    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver88.exe");

        browser = new ChromeDriver();
        browser.get("https://www.bing.com/");
    }

    public static void searchByKeyword(String keyword){
        waitForTimeByXpath("//*[@id=\"sb_form_q\"]");
        WebElement searchField = browser.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
        searchField.sendKeys(keyword);
        searchField.sendKeys(Keys.ENTER);
    }

    public static void compareResults(){
        WebElement countResults = browser.findElement(By.className("sb_count"));
        System.out.println(countResults.getText());
        String results = countResults.getText().replaceAll("[A-Za-z]", "").replaceAll("[ ,]", "");

        int results2 = Integer.parseInt(results);

        if (results2>50000){
            System.out.println("Rasytojas yra populiarus");
        }else {
            System.out.println("Rasytojas nera labai populiarus");
        }
    }

    public static void waitForTimeByXpath(String element){
        WebDriverWait wait = new WebDriverWait(browser, WAIT_FOR_TIME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    }

    public static void close(){
        browser.close();
    }

}
