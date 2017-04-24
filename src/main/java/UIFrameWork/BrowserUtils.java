package UIFrameWork;

import Utils.ReportUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Administrator on 2017-04-23.
 */
public class BrowserUtils extends Browser {
    ReportUtils reporter = new ReportUtils();
    public WebDriver getDriver(){
        return dr;
    }


    /**打开网页*/
    public void openWeb(String url){
        if (url.equals("") || url == null){
            reporter.log("没有url值");
            return;
        }else{
        dr.get(url);
        }
        reporter.log("打开【"+url+"】网址");
    }
    /**关闭窗口*/
    public void closeWeb(){
        dr.close();
    }

    /**暂停一下，不要急*/
    public void pause(long milliseconds){
        if (milliseconds<=0){
            return;
        }else {
            try {
                Thread.sleep(milliseconds);
                reporter.log("等待："+milliseconds+"毫秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
                reporter.error("暂停错误："+e.getMessage());
            }
        }
    }

    /**关闭浏览器*/
    public void quit(){
        dr.quit();
    }

    /**获取当前url*/
    public String getCurrentUrl(){
        String url = dr.getCurrentUrl();
        reporter.log("当前页面的url为："+url);
        return url;
    }

    /**刷新页面*/
    public void refresh(){
        dr.navigate().refresh();
    }

    /**后退*/
    public void back(){
        dr.navigate().back();
    }

    /**前进*/
    public void forward(){
        dr.navigate().forward();
    }

    /**切换窗口*/
    public WebDriver switchTo_windows(String window){
        reporter.log("切换到"+window+"窗口");
        return dr.switchTo().window(window);
    }

    /**切换内嵌页面*/
    public WebDriver switch_Frame(String frameName){
        reporter.log("切换到"+frameName);
        return dr.switchTo().frame(frameName);
    }

    /**切换内嵌页面*/
    public WebDriver switch_Frame(int index){
        reporter.log("切换到第"+index+"个内嵌页面");
        return dr.switchTo().frame(index);
    }

    /**切换内嵌页面*/
    public WebDriver switch_Frame(WebElement element){
        return dr.switchTo().frame(element);
    }

}
