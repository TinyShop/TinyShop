package UIFrameWork;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by Administrator on 2017-04-23.
 */
public class WebElementUtils extends BrowserUtils{
    private WebElement element;
    public WebElement findElement_id(String id){
        if (id == null) {
            reporter.log("id为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.id(id));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+id+"找不到元素");
                return null;
            }
        }
    }

    public WebElement findElement_Name(String name){
        if (name == null) {
            reporter.log("name为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.name(name));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+name+"找不到元素");
                return null;
            }
        }
    }

    public WebElement findElement_class(String className){
        if (className == null) {
            reporter.log("className为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.className(className));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+className+"找不到元素");
                return null;
            }
        }
    }

    public WebElement findElement_xpath(String xpath){
        if (xpath == null) {
            reporter.log("xpath为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.xpath(xpath));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+xpath+"找不到元素");
                return null;
            }
        }
    }

    public WebElement findElement_tagName(String tagName){
        if (tagName == null) {
            reporter.log("tagName为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.tagName(tagName));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+tagName+"找不到元素");
                return null;
            }
        }
    }

    public WebElement fndElement_css(String selector){
        if (selector == null) {
            reporter.log("selector为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.cssSelector(selector));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+selector+"找不到元素");
                return null;
            }
        }
    }

    public WebElement findElement_link(String text){
        if (text == null) {
            reporter.log("输入的text参数为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.linkText(text));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+text+"找不到元素");
                return null;
            }
        }
    }

    public WebElement findElement_partial(String par){
        if (par == null) {
            reporter.log("输入的par参数为空，不定位");
            return null;
        }else {
            try{
                element = dr.findElement(By.partialLinkText(par));
                return element;
            }catch (NoSuchElementException e){
                reporter.error("通过"+par+"找不到元素");
                return null;
            }
        }
    }

    public void click(){
        element.click();
    }

    public void sendKeys(CharSequence keys){
       pause(1000);
       element.sendKeys(keys);
    }

    public static void main(String[] args) {
        WebElementUtils elementUtils = new WebElementUtils();
        elementUtils.openWeb("http://www.baidu.com");
        elementUtils.findElement_id("kw").sendKeys("湖南");
    }
}
