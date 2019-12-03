package com.klis.autoresume.login;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

/**
 * @author k
 */
@Data
@Slf4j
public class LoginUser {
    private static String loginTitle = "";
    private static String userTitle = "";
    public static void loginLoop(String username,String password,WebDriver driver){
        //判断标题是否登陆成功
//        log.info(driver.getWindowHandle());
//        while(loginTitle.equals(userTitle)){
//            loginTitle = driver.getCurrentUrl();
//            userTitle = driver.getCurrentUrl();
//            log.info(driver.getWindowHandle());
//        }
        login(username,password,driver);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("登录成功正在进入搜索阶段");
    }
    /** 登陆界面操作 */
    private static void login(String userName,String password,WebDriver driver) {
        // 输入登陆用户
        WebElement loginName = driver.findElement(By.name("account"));
        loginName.clear();
        loginName.sendKeys(userName);
        //输入登陆密码
        WebElement loginPassword = driver.findElement(By.name("password"));
        loginPassword.clear();
        loginPassword.sendKeys(password);

        // 获取滑动组件
        WebElement loginContorll = driver.findElement(By.id("nc_1_n1z"));
        // Action 拖拉事件
        Actions action = new Actions(driver);
        // 按住不放
        action.clickAndHold(loginContorll).perform();
        //托到指定位置然后释放
        action.moveByOffset(277,0).release().perform();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 点击登陆按钮
        driver.findElement(By.cssSelector(".sign-pwd .btn")).click();
    }
}
