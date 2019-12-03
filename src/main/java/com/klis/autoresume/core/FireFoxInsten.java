package com.klis.autoresume.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

/**
 * @author k
 */

@Data
@Slf4j
@Component
public class FireFoxInsten {
    /** 登陆的地址 */
    private final String URL = "https://login.zhipin.com/?ka=header-login";
    private WebDriver masterDriver;

    public FireFoxInsten(){
        this.openFirefox();
        this.loginWeb();
        new CoreProcess(masterDriver);
    }
    /** 开启firefox进程 */
    private void openFirefox(){
        System.setProperty("webdriver.gecko.driver","C:\\Users\\k\\Documents\\geckodriver.exe");
        log.info("正在启动firefox浏览器");
        // 开启firefox浏览器
        if (masterDriver == null){
            this.masterDriver = new FirefoxDriver();
        }
        log.info("firefox启动成功");
    }

    /** 进去登陆界面 */
    private void loginWeb(){
        // 打开URL
        log.info("打开登陆网站");
        this.masterDriver.get(this.URL);
    }

    /** 关闭firefox进程 */
    public void closeFirefox(){
        if (this.masterDriver != null){
            this.masterDriver.close();
        }
    }
}
