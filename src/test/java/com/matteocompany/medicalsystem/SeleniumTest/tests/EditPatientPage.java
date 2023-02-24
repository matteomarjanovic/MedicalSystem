package com.matteocompany.medicalsystem.SeleniumTest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPatientPage extends PageObject{
    @FindBy(xpath="/html/body/h1")
    private WebElement pageTitle;
    @FindBy(xpath="/html/body/form/input[2]")
    private WebElement patientUsernameField;
    @FindBy(xpath="/html/body/form/input[3]")
    private WebElement patientNameField;
    @FindBy(xpath="/html/body/form/input[4]")
    private WebElement patientSurnameField;
    @FindBy(xpath="/html/body/form/input[5]")
    private WebElement patientAddressField;
    @FindBy(xpath="/html/body/form/input[6]")
    private WebElement patientPhoneNumberField;
    @FindBy(xpath="/html/body/form/input[7]")
    private WebElement submitButton;
    @FindBy(xpath="/html/body/a[1]")
    private WebElement goBackButton;
    @FindBy(xpath="/html/body/a[2]")
    private WebElement logOutButton;

    public EditPatientPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return pageTitle.getText();
    }
    public void setPatientUsername(String newString) {
        patientUsernameField.clear();
        patientUsernameField.sendKeys(newString);
    }
    public void setPatientName(String newString) {
        patientNameField.clear();
        patientNameField.sendKeys(newString);
    }
    public void setPatientSurname(String newString) {
        patientSurnameField.clear();
        patientSurnameField.sendKeys(newString);
    }
    public void setPatientAddress(String newString) {
        patientAddressField.clear();
        patientAddressField.sendKeys(newString);
    }
    public void setPatientPhoneNumber(String newString) {
        patientPhoneNumberField.clear();
        patientPhoneNumberField.sendKeys(newString);
    }
    public AdminPage submit() {
        submitButton.click();
        return new AdminPage(driver);
    }
    public AdminPage goBack() {
        goBackButton.click();
        return new AdminPage(driver);
    }
    public LoginPage logOut() {
        logOutButton.click();
        return new LoginPage(driver);
    }
}
