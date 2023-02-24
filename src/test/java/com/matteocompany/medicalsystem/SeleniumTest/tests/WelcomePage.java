package com.matteocompany.medicalsystem.SeleniumTest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends PageObject{
    @FindBy(xpath="/html/body/h1")
    private WebElement pageTitle;
    @FindBy(xpath="/html/body/a")
    private WebElement loginLink;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return pageTitle.getText();
    }
    public LoginPage goToLogin() {
        loginLink.click();
        return new LoginPage(driver);
    }

}
