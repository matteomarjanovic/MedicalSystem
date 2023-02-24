package com.matteocompany.medicalsystem.SeleniumTest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCurrentDoctorPage extends PageObject{
    @FindBy(xpath="/html/body/h1")
    private WebElement pageTitle;
    @FindBy(xpath="/html/body/table")
    private WebElement doctorTable;
    @FindBy(xpath="/html/body/table/tbody/tr[1]/td[1]")
    private WebElement firstDoctorUsername;
    @FindBy(xpath="/html/body/table/tbody/tr[1]/td[2]")
    private WebElement firstDoctorName;
    @FindBy(xpath="/html/body/table/tbody/tr[1]/td[3]")
    private WebElement firstDoctorSurname;
    @FindBy(xpath="/html/body/table/tbody/tr[1]/td[4]/a")
    private WebElement firstDoctorChooseButton;
    @FindBy(xpath="/html/body/a[1]")
    private WebElement goBackButton;
    @FindBy(xpath="/html/body/a[2]")
    private WebElement logOutButton;

    public AddCurrentDoctorPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return pageTitle.getText();
    }
    public String getFirstDoctorUsername(){
        return firstDoctorUsername.getText();
    }
    public String getFirstDoctorName(){
        return firstDoctorName.getText();
    }
    public String getFirstDoctorSurname(){
        return firstDoctorSurname.getText();
    }

    public AdminPage chooseFirstDoctor(){
        this.firstDoctorChooseButton.click();
        return new AdminPage(driver);
    }
}
