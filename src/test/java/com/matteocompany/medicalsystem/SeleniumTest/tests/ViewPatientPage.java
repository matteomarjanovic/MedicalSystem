package com.matteocompany.medicalsystem.SeleniumTest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewPatientPage extends PageObject{
    @FindBy(xpath="/html/body/h1")
    private WebElement pageTitle;
    @FindBy(xpath="/html/body/ul/li[1]")
    private WebElement patientUsernameField;
    @FindBy(xpath="/html/body/ul/li[2]")
    private WebElement patientNameField;
    @FindBy(xpath="/html/body/ul/li[3]")
    private WebElement patientSurnameField;
    @FindBy(xpath="/html/body/ul/li[4]")
    private WebElement patientAddressField;
    @FindBy(xpath="/html/body/ul/li[5]")
    private WebElement patientPhoneNumberField;
    @FindBy(xpath="/html/body/ul/li[6]")
    private WebElement patientCurrentDoctorField;
    @FindBy(xpath="/html/body/form/input[7]")
    private WebElement submitButton;
    @FindBy(xpath="/html/body/span/a")
    private WebElement goBackButtonAdmin;
    @FindBy(xpath="/html/body/a")
    private WebElement logOutButton;

    public ViewPatientPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return pageTitle.getText();
    }
    public String getPatientUsername(String newString) {
        return patientUsernameField.getText();
    }
    public String getPatientName() {
        return patientNameField.getText();
    }
    public String getPatientSurname() {
        return patientSurnameField.getText();
    }
    public String getPatientAddress() {
        return patientAddressField.getText();
    }
    public String getPatientPhoneNumber() {
        return patientPhoneNumberField.getText();
    }
    public AdminPage goBackAdmin() {
        goBackButtonAdmin.click();
        return new AdminPage(driver);
    }
    public LoginPage logOut() {
        logOutButton.click();
        return new LoginPage(driver);
    }
}
