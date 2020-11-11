import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试selenium〉
 *
 * @author Claire
 * @create 2020/11/10
 * @since 1.0.0
 */
public class StartSeleniumTest {
    @Test
    public void startSelenium(){
        WebDriver wd =new ChromeDriver();
        wd.get("https://home.testing-studio.com/");
        wd.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
    }
}
