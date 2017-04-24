package UIFrameWork;

import Utils.ReportUtils;
import Utils.SeleniumConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by Administrator on 2017-04-23.
 */
public class Browser {
    public WebDriver dr;
    public ReportUtils report = new ReportUtils();
    public Browser() {
            setUpDriver(SeleniumConfig.DRIVER_TYPE);
            maxBrowser();
    }
    private void setUpDriver(int driverType){
        switch (driverType){
            case 1:
                setUpChrome();
                break;
            case 2:
                setUpFirefox();
                break;
        }
    }
    private void setUpFirefox(){
        System.setProperty("webdriver.gecko.driver",SeleniumConfig.FIREFOX);
        dr = new FirefoxDriver();
        report.log("启动火狐浏览器");
    }
    private void setUpChrome() {
        System.setProperty("webdriver.chrome.driver",SeleniumConfig.CHROME);
        dr = new ChromeDriver();
        report.log("启动谷歌浏览器");
    }
    private void maxBrowser(){
        dr.manage().window().maximize();
    }

    public static void main(String[] args) {
        Browser browser = new Browser();
    }
}
