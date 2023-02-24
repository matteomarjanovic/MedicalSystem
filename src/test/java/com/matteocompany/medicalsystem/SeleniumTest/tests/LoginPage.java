package com.matteocompany.medicalsystem.SeleniumTest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject{
    @FindBy(xpath="/html/body/div/form/h2")
    private WebElement pageTitle;
    @FindBy(xpath="//*[@id=\"username\"]")
    private WebElement usernameField;
    @FindBy(xpath="//*[@id=\"password\"]")
    private WebElement passwordField;
    @FindBy(xpath="/html/body/div/form/button")
    private WebElement signInButton;
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return pageTitle.getText();
    }
    public void setUsernameField(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public AdminPage loginAdmin() {
        this.setUsernameField("admin");
        this.setPasswordField("admin");
        signInButton.click();
        return new AdminPage(driver);
    }
    public ViewPatientPage loginPatient(String username, String password) {
        this.setUsernameField(username);
        this.setPasswordField(password);
        signInButton.click();
        return new ViewPatientPage(driver);
    }
}
