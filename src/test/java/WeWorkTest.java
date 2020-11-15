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

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 〈一句话功能简述〉<br>
 * 〈企业微信web自动化测试〉
 *
 * @author Claire
 * @create 2020/11/11
 * @since 1.0.0
 */
public class WeWorkTest {

    private static WebDriver driver ;

    static void needLogin() throws InterruptedException, IOException {
        //扫码登录，写入cookie
        WebDriver driver = new ChromeDriver();driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        //sleep 15s
        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);
        System.exit(0);
    }

    @BeforeAll
    static void logined() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        if (file.exists()) {
            //利用cookie复用session登录
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

    /**
     * 直接进行selenium方法调用进行web自动化
     */
//    @Test
//    void addMembers() {
//        driver.findElement(By.cssSelector("#menu_contacts .frame_nav_item_title")).click();
//        driver.findElement(By.linkText("添加成员")).click();
//        driver.findElement(By.id("username")).sendKeys("第一位测试者2");
//        driver.findElement(By.id("memberAdd_english_name")).sendKeys("firstone");
//        driver.findElement(By.cssSelector(".member_edit_item_Account")).click();
//        driver.findElement(By.id("memberAdd_acctid")).sendKeys("hahahaha3");
//        driver.findElement(By.id("memberAdd_phone")).sendKeys("18270718114");
//        driver.findElement(By.cssSelector(".member_colRight_operationBar:nth-child(3) > .ww_btn_Blue")).click();
//    }

    /**
     * 封装具体操作后进行selenium方法调用进行web自动化
     */
    @Test
    void addMembers(){
        click(By.linkText("添加成员"));
        sendKeys(By.name("username"), "第二个");
        sendKeys(By.name("acctid"), "第二个");
        sendKeys(By.name("mobile"), "15600534001");
        click(By.linkText("保存"));
    }
    @Test
    void search(){

    }
    @Test
    void departmentSearch() {
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"), "工程效能");
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();//取所有文本
        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));
        content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        assertTrue(content.contains("无任何成员"));
    }
//封装复用
    void click(By by) {
        driver.findElement(by).click();

    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);

    }
    @AfterAll
    public static void teardown() {
        driver.quit();
    }
}
