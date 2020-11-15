package WeWorkTestPom;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


import static WeWorkTestPom.MainPage.teardown;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 〈一句话功能简述〉<br>
 * 〈业务po用例〉
 * 不要涉及底层driver
 *
 * @author Claire
 * @create 2020/11/14
 * @since 1.0.0
 */
public class ContactP0Test extends BasePage {
    private static MainPage main;

    @BeforeAll
    static void beforeall() throws IOException, InterruptedException {
        main = new MainPage();
    }

    @Test
    void testAddMember() throws IOException, InterruptedException {
        //打开页面
        //复用session登录

//        跳转页面
//        添加成员
        //导航进入一个添加成员
        ContactPage contactPage = main.MemberAdd();
//      contactPage.addMember("test2", "test2", "15511112222");
        contactPage.searchDepart("工程效能");
        String content = contactPage.getPartyInfo();
        System.out.println(content);
        assertTrue(content.contains("无任何成员"));
//        assert contactPage.search("test2").getInfo();
    }

    @Test
//链式调用
    void testDepartSearchchain() throws IOException, InterruptedException {
        assertTrue(new MainPage().MemberAdd().searchDepart("工程效能").getPartyInfo().contains("无任何成员"));
    }
    //todo ：部门新建  部门搜索  部门更新  部门内添加成员  导入成员

    @Test
    void testDepartAdd() {
        String departName = "depart_1114";
        main.MemberAdd().addDepart(departName).searchDepart(departName).getPartyInfo().contains(departName);
    }


    @AfterAll
    public static void afterall() {
        teardown();
    }
}