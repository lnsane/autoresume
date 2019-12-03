package com.klis.autoresume.searchjob;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author k
 */
@Data
@Slf4j
public class Search {
    private static final String JOB_KEY = "java开发";
    public static void searchJob(WebDriver driver){
        searchProcess(driver);
        moveToElement(driver);
    }

    /**
     * 职位搜索
     * @param driver 主进程
     */
    private static void searchProcess(WebDriver driver){
        WebElement search = driver.findElement(By.cssSelector(".ipt-ctrl > input"));
        search.sendKeys(JOB_KEY);
        WebElement sumbit = driver.findElement(By.cssSelector(".btn-search"));
        sumbit.click();
    }
    private static void moveToElement(WebDriver driver){
        String keyHandle = driver.getWindowHandle();
        while(true){
            for (int i = 1 ; i <= 28;i++){
                Actions action = new Actions(driver);
                WebElement jobList = driver.findElement(By.cssSelector("li:nth-child(" + i +") > .job-primary > .info-primary"));
                jobList.click();
                try {
                    TimeUnit.SECONDS.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("当前句柄：{}",driver.getWindowHandle());
                getNowHandle(driver.getWindowHandles(),driver);
                driver.switchTo().window(keyHandle);
            }
            driver.findElement(By.cssSelector(".next")).click();
        }

    }
    private static void getNowHandle(Set<String> set,WebDriver driver){
        Iterator<String> nextHandle = set.iterator();
        String nowHandle = null;
        while (nextHandle.hasNext()) {
            nowHandle  = nextHandle.next();;
        }
        driver.switchTo().window(nowHandle);
        log.info("当前句柄：{}",driver.getWindowHandle());
        communicationJob(driver);
        driver.close();
    }
    private static void communicationJob(WebDriver driver){
        String CHAT_TEXT = "你好,我非常对这份工作有兴趣,能否让技术看一下我简历,约面试时间,这是我的简历你看一下,谢谢！";
        WebElement keyBtn = driver.findElement(By.cssSelector(".btn-container > .btn"));
        String text = keyBtn.getText();
        if ("立即沟通".equals(text)){
            keyBtn.click();
            WebElement getContinueBtn = driver.findElement(By.cssSelector(".btn-sure"));
            getContinueBtn.click();
            // TODO 需要接入一个聊天系统
//            WebElement getChatText = driver.findElement(By.cssSelector(".chat-input"));
//            getChatText.sendKeys(CHAT_TEXT);
        }
    }
}
