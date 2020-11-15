package WeWorkTestPom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈主页面〉
 * 实现封装，selenium的封装要放在这里
 *
 * @author Claire
 * @create 2020/11/15
 * @since 1.0.0
 */
public class MainPage extends BasePage {

    void needLogin() throws InterruptedException, IOException {
        //扫码登录，写入cookie
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        //sleep 15s
        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);
        System.exit(0);
    }

    void logined() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        if (file.exists()) {
            //利用cookie复用session登录
//            if (System.getenv("browser") == "chrome") {
//                driver = new ChromeDriver();
//            } else if (System.getenv("browser") == "firefox") {
//                driver = new FirefoxDriver();
//            }
            //增加隐式等待
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            //读Cookies
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
            System.out.println(cookies);
            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });
            driver.navigate().refresh();
        } else {
            needLogin();
            System.out.println("重新获取cookie");
        }
    }

    //统一初始化一个实例变量
    public MainPage() throws IOException, InterruptedException {
        //初始化你的selenium 复用session 打开网站
        this.logined();
    }

    public ContactPage MemberAdd() {
        //进入通讯录
        click(By.id("menu_contacts"));
        //传递selenium的driver给另外一个po
        //po原则4  跳转或进入新页面使用返回新的po来模拟
        return new ContactPage(driver);

    }
    public static void teardown(){
        driver.quit();
        }

}
