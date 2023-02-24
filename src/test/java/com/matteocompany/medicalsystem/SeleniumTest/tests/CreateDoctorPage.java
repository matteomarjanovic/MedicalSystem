package com.matteocompany.medicalsystem.SeleniumTest.tests;

import com.matteocompany.medicalsystem.Model.Specialization;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateDoctorPage extends PageObject{
    @FindBy(xpath="/html/body/h1")
    private WebElement pageTitle;
    @FindBy(xpath="/html/body/form/input[2]")
    private WebElement doctorUsernameField;
    @FindBy(xpath="/html/body/form/input[3]")
    private WebElement doctorNameField;
    @FindBy(xpath="/html/body/form/input[4]")
    private WebElement doctorSurnameField;
    @FindBy(xpath="/html/body/form/input[5]")
    private WebElement doctorAddressField;
    @FindBy(xpath="/html/body/form/input[6]")
    private WebElement doctorPhoneNumberField;
    @FindBy(xpath="/html/body/form/select")
    private WebElement doctorSpecializationField;
    @FindBy(xpath="/html/body/form/input[7]")
    private WebElement submitButton;
    @FindBy(xpath="/html/body/a[1]")
    private WebElement goBackButton;
    @FindBy(xpath="/html/body/a[2]")
    private WebElement logOutButton;

    public CreateDoctorPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return pageTitle.getText();
    }
    public void setDoctorUsername(String newString) {
        doctorUsernameField.clear();
        doctorUsernameField.sendKeys(newString);
    }
    public void setDoctorName(String newString) {
        doctorNameField.clear();
        doctorNameField.sendKeys(newString);
    }
    public void setDoctorSurname(String newString) {
        doctorSurnameField.clear();
        doctorSurnameField.sendKeys(newString);
    }
    public void setDoctorAddress(String newString) {
        doctorAddressField.clear();
        doctorAddressField.sendKeys(newString);
    }
    public void setDoctorPhoneNumber(String newString) {
        doctorPhoneNumberField.clear();
        doctorPhoneNumberField.sendKeys(newString);
    }
    public void setDoctorSpecialization(String newString) {
        Select specSelection = new Select(doctorSpecializationField);
        specSelection.selectByVisibleText(newString);
    }
    public void insertMockValues() {
        this.setDoctorUsername("DoctorSample");
        this.setDoctorName("DoctorName");
        this.setDoctorSurname("DoctorSurname");
        this.setDoctorAddress("DoctorAddress");
        this.setDoctorPhoneNumber("DoctorPhoneNumber");
        this.setDoctorSpecialization(Specialization.PEDIATRICIAN.toString());
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
