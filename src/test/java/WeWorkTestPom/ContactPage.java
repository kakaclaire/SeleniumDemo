package WeWorkTestPom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Claire
 * @create 2020/11/15
 * @since 1.0.0
 */
public class ContactPage extends BasePage{
    //原则2  不要暴露页面内部细节
    private By parterInfo = By.cssSelector(".js_party_info");

    public ContactPage(WebDriver driver) {
        //保存driver到自己的实例
        super(driver);
    }
    //po原则6  添加成功的时候与添加失败返回的页面是不同的，需要封装为不同的方法
    public ContactPage addMember(String username, String acctid, String mobile){
        return this;
    }
    //po原则6  添加失败返回的页面是不同的，需要封装为不同的方法
    public ContactPage addMemberFail(String username, String acctid, String mobile){
        return this;
    }
    //po原则5，不要实现所有的方法，按需封装
    public ContactPage searchDepart(String departName){
        //po原则1  用公共方法代表页面所提供的功能
        //po原则3  通常不要在po方法加断言
        sendKeys(By.id("memberSearchInput"), "工程效能");
        String content = driver.findElement(parterInfo).getText();//取所有文本
        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));

        return this;
    }
    public String getPartyInfo(){
        String content = driver.findElement(parterInfo).getText();
        System.out.println(content);
        return content;
    }

    public ContactPage addDepart(String departName) {
//        todo:添加部门
//        click(By.cssSelector("#js_contacts59 > div > div.member_colLeft > div > div.member_colLeft_top.member_colLeft_top_BorderBottom > a"));
//        click(By.cssSelector("member_colLeft_top_addBtn"));
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),departName);
        click(By.linkText("选择所属部门"));
        driver.findElements(By.linkText("工程效能")).get(1).click();
        click(By.linkText("确定"));
        return this;
    }


}
