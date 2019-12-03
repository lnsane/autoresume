package com.klis.autoresume.core;

import com.klis.autoresume.login.LoginUser;
import com.klis.autoresume.searchjob.Search;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author k
 */
public class CoreProcess {
    private final String USER_NAME = "18357128255";
    private final String PASS_WORD = "wANG931125";
    public CoreProcess(WebDriver driver){
        LoginUser.loginLoop(USER_NAME,PASS_WORD,driver);
        Search.searchJob(driver);
    }
}
