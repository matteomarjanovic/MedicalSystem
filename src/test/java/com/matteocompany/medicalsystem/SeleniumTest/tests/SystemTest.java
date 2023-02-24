package com.matteocompany.medicalsystem.SeleniumTest.tests;

import com.matteocompany.medicalsystem.Controller.MedicalSystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicalSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SystemTest extends BaseTest {
    @Test
    public void testViewAdmin() {
        driver.get("http://localhost:8080/");

        WelcomePage welcomePage = new WelcomePage(driver);
        LoginPage loginPage = welcomePage.goToLogin();
        AdminPage adminPage = loginPage.loginAdmin();
        assertEquals("Admin", adminPage.getTitle());
    }
    @Test
    public void testAddDoctor() {
        driver.get("http://localhost:8080/");

        WelcomePage welcomePage = new WelcomePage(driver);
        LoginPage loginPage = welcomePage.goToLogin();
        AdminPage adminPage = loginPage.loginAdmin();
        CreateDoctorPage createDoctorPage = adminPage.addNewDoctor();
        assertEquals("Create a new doctor", createDoctorPage.getTitle());
        createDoctorPage.insertMockValues();
        adminPage = createDoctorPage.submit();
        assertEquals("DoctorSample", adminPage.getLastDoctorUsername());
    }
    @Test
    public void testSetCurrentDoctor() {
        driver.get("http://localhost:8080/");

        WelcomePage welcomePage = new WelcomePage(driver);
        LoginPage loginPage = welcomePage.goToLogin();
        AdminPage adminPage = loginPage.loginAdmin();
        AddCurrentDoctorPage addCurrentDoctorPage = adminPage.addDoctorToFirstPatient();
        String chosenDoctorName = addCurrentDoctorPage.getFirstDoctorName();
        String chosendoctorSurname = addCurrentDoctorPage.getFirstDoctorSurname();
        adminPage = addCurrentDoctorPage.chooseFirstDoctor();
        assertEquals(chosenDoctorName + " " + chosendoctorSurname, adminPage.getFirstPatientCurrentDoctor());
    }
    @Test
    public void testDeleteDoctor() {
        driver.get("http://localhost:8080/");

        WelcomePage welcomePage = new WelcomePage(driver);
        LoginPage loginPage = welcomePage.goToLogin();
        AdminPage adminPage = loginPage.loginAdmin();
        assertEquals("angelicamancini", adminPage.getFirstDoctorUsername());
        AddCurrentDoctorPage addCurrentDoctorPage = adminPage.addDoctorToFirstPatient();
        adminPage = addCurrentDoctorPage.chooseFirstDoctor();
        adminPage = adminPage.deleteFirstDoctor();
        assertEquals("vincenzotrevisano", adminPage.getFirstDoctorUsername());
        assertEquals("Add a doctor", adminPage.getFirstPatientCurrentDoctor());
    }
    @Test
    public void testEditPatient() {
        driver.get("http://localhost:8080/");

        WelcomePage welcomePage = new WelcomePage(driver);
        LoginPage loginPage = welcomePage.goToLogin();
        AdminPage adminPage = loginPage.loginAdmin();
        assertEquals("mariorossi", adminPage.getFirstPatientUsername());
        EditPatientPage editPatientPage = adminPage.editFirstPatient();
        editPatientPage.setPatientUsername("mariaverdi");
        editPatientPage.setPatientName("Maria");
        editPatientPage.setPatientSurname("Verdi");
        editPatientPage.setPatientAddress("Nuova Via");
        editPatientPage.setPatientPhoneNumber("3333333333");
        adminPage = editPatientPage.submit();
        assertEquals("mariaverdi", adminPage.getFirstPatientUsername());
        ViewPatientPage viewPatientPage = adminPage.viewFirstPatient();
        assertEquals("Name: Maria", viewPatientPage.getPatientName());
        assertEquals("Surname: Verdi", viewPatientPage.getPatientSurname());
        assertEquals("Address: Nuova Via", viewPatientPage.getPatientAddress());
        assertEquals("Phone number: 3333333333", viewPatientPage.getPatientPhoneNumber());
    }
    @Test
    public void testLoginLogoutLogin() {
        driver.get("http://localhost:8080/");

        WelcomePage welcomePage = new WelcomePage(driver);
        LoginPage loginPage = welcomePage.goToLogin();
        AdminPage adminPage = loginPage.loginAdmin();
        loginPage = adminPage.logOut();
        assertEquals("Please sign in", loginPage.getTitle());
        ViewPatientPage patientView = loginPage.loginPatient("mariorossi", "password");
        assertEquals("Detail of patient:", patientView.getTitle());
        loginPage = patientView.logOut();
        assertEquals("Please sign in", loginPage.getTitle());
    }



}
