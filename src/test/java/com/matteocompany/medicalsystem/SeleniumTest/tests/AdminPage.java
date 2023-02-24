package com.matteocompany.medicalsystem.SeleniumTest.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends PageObject{
    @FindBy(xpath="/html/body/h1")
    private WebElement pageTitle;
    @FindBy(xpath="/html/body/table[1]")
    private WebElement patientTable;
    @FindBy(xpath="/html/body/table[1]/tbody/tr[1]/td[1]")
    private WebElement firstPatientUsername;
    @FindBy(xpath="/html/body/table[1]/tbody/tr[1]/td[2]")
    private WebElement firstPatientName;
    @FindBy(xpath="/html/body/table[1]/tbody/tr[1]/td[3]")
    private WebElement firstPatientSurname;
    @FindBy(xpath="/html/body/table[1]/tbody/tr[1]/td[4]/a")
    private WebElement firstPatientAddDoctorButton;
    @FindBy(xpath="/html/body/table[1]/tbody/tr[1]/td[4]")
    private WebElement firstPatientCurrentDoctor;
    @FindBy(xpath="/html/body/table[1]/tbody/tr[1]/td[5]/a")
    private WebElement firstPatientViewButton;
    @FindBy(xpath="/html/body/table[1]/tbody/tr[1]/td[6]/a")
    private WebElement firstPatientEditButton;
    @FindBy(xpath="/html/body/a[1]")
    private WebElement addPatientButton;
    @FindBy(xpath="/html/body/table[2]")
    private WebElement doctorTable;
    @FindBy(xpath="/html/body/table[2]/tbody/tr[1]/td[1]")
    private WebElement firstDoctorUsername;
    @FindBy(xpath="/html/body/table[2]/tbody/tr[1]/td[2]")
    private WebElement firstDoctorName;
    @FindBy(xpath="/html/body/table[2]/tbody/tr[1]/td[3]")
    private WebElement firstDoctorSurname;
    @FindBy(xpath="/html/body/table[2]/tbody/tr[1]/td[4]/a")
    private WebElement firstDoctorViewButton;
    @FindBy(xpath="/html/body/table[2]/tbody/tr[1]/td[5]/a")
    private WebElement firstDoctorDeleteButton;
    @FindBy(xpath="/html/body/a[2]")
    private WebElement addDoctorButton;
    @FindBy(xpath="/html/body/a[3]")
    private WebElement logOutButton;

    public AdminPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return pageTitle.getText();
    }
    public String getFirstPatientUsername(){
        return firstPatientUsername.getText();
    }
    public String getFirstPatientCurrentDoctor(){
        return firstPatientCurrentDoctor.getText();
    }
    public String getFirstDoctorUsername(){
        return firstDoctorUsername.getText();
    }
    public int getPatientTableSize(){
        return patientTable.findElements(By.tagName("tr")).size();
    }
    public int getDoctorTableSize(){
        return doctorTable.findElements(By.tagName("tr")).size();
    }
    public CreateDoctorPage addNewDoctor(){
        this.addDoctorButton.click();
        return new CreateDoctorPage(driver);
    }
    public EditPatientPage editFirstPatient(){
        this.firstPatientEditButton.click();
        return new EditPatientPage(driver);
    }
    public ViewPatientPage viewFirstPatient(){
        this.firstPatientViewButton.click();
        return new ViewPatientPage(driver);
    }
    public AdminPage deleteFirstDoctor(){
        this.firstDoctorDeleteButton.click();
        return new AdminPage(driver);
    }
    public AddCurrentDoctorPage addDoctorToFirstPatient(){
        this.firstPatientAddDoctorButton.click();
        return new AddCurrentDoctorPage(driver);
    }
    public String getLastDoctorUsername(){
        int tableSize = this.getDoctorTableSize();
        WebElement lastDoctorUsername = driver.findElement(By.xpath(String.format("/html/body/table[2]/tbody/tr[%d]/td[1]", tableSize-1)));
        return lastDoctorUsername.getText();
    }
    public LoginPage logOut() {
        this.logOutButton.click();
        return new LoginPage(driver);
    }
}
