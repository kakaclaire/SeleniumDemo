import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈企业微信web自动化测试〉
 *
 * @author Claire
 * @create 2020/11/11
 * @since 1.0.0
 */
public class WeWorkTest {

    public static WebDriver driver = new ChromeDriver();
    @BeforeAll
    public static void logined() throws IOException, InterruptedException {

        //增加隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        Thread.sleep(15000);
//        //写入cookie
//        Set<Cookie> cookies = driver.manage().getCookies();
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        mapper.writeValue(new File("cookies.yaml"), cookies);
//        Thread.sleep(30000);
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

    }

    @Test
    void addMembers(){
        driver.findElement(By.cssSelector("#menu_contacts .frame_nav_item_title")).click();
        driver.findElement(By.linkText("添加成员")).click();
        driver.findElement(By.id("username")).sendKeys("第一位测试者2");
        driver.findElement(By.id("memberAdd_english_name")).sendKeys("firstone");
        driver.findElement(By.cssSelector(".member_edit_item_Account")).click();
        driver.findElement(By.id("memberAdd_acctid")).sendKeys("hahahaha3");
        driver.findElement(By.id("memberAdd_phone")).sendKeys("18270718114");
        driver.findElement(By.cssSelector(".member_colRight_operationBar:nth-child(3) > .ww_btn_Blue")).click();

    }
    @AfterAll
    public static void teardown(){
        driver.quit();
    }
}
