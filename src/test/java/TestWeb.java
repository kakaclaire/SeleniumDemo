import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;

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
 * 〈〉
 *
 * @author Claire
 * @create 2020/11/11
 * @since 1.0.0
 */
public class TestWeb {
    @Test
    void testSearch(){
        WebDriver driver=new ChromeDriver();
        //增加隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
        //web的核心是css定位的，id搜索的底层也是从cessSelector实现，xpath也是核心
//        driver.findElement(By.cssSelector("#search-button")).click();
        driver.findElement(By.cssSelector(".search-dropdown .d-icon-search")).click();
        driver.findElement(By.cssSelector("#search-term")).sendKeys("selenium");
    }
    @Test
    void testlogin() throws IOException,InterruptedException {
        WebDriver driver=new ChromeDriver();
        //增加隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        //sleep 20
        Thread.sleep(15000);
        //写入cookie
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);
//        //读cookie
//        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
//        TypeReference typeReference=new TypeReference<List<HashMap<String,Object>>>(){};
//        System.out.println(cookies);




    }
}
