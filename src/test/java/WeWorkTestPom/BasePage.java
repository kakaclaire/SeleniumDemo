package WeWorkTestPom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 〈一句话功能简述〉<br>
 * 〈po基本实现-基础父类〉
 *
 * @author Claire
 * @create 2020/11/14
 * @since 1.0.0
 */
public class BasePage {
    static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver=driver;
    }

    public BasePage() {
    }


    //封装复用
    void click(By by) {
        driver.findElement(by).click();

    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);

    }


}
