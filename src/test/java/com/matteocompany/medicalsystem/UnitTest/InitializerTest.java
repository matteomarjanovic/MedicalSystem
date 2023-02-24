package com.matteocompany.medicalsystem.UnitTest;

import com.matteocompany.medicalsystem.Controller.MedicalSystemApplication;
import com.matteocompany.medicalsystem.Model.Doctor;
import com.matteocompany.medicalsystem.Model.Patient;
import com.matteocompany.medicalsystem.Model.Specialization;
import com.matteocompany.medicalsystem.Repository.DBInitializer;
import com.matteocompany.medicalsystem.Repository.DoctorRepository;
import com.matteocompany.medicalsystem.Repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicalSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InitializerTest {
    @Autowired
    protected PatientRepository patientRepository;
    @Autowired
    protected DoctorRepository doctorRepository;
    @Autowired
    protected DoctorRepository adminRepository;
    @Autowired
    protected DBInitializer DBInitializer;


    @Test
    public void initializerTest() {
        DBInitializer.initialize();
        Iterable<Doctor> doctorIterator = doctorRepository.findAll();
        Doctor doctor = doctorIterator.iterator().next();
        assertEquals(doctor.getUsername(), "angelicamancini");
        assertEquals(doctor.getName(), "Angelica");
        assertEquals(doctor.getSurname(), "Mancini");
        assertEquals(doctor.getAddress(), "Via di Esempio 8, Verona");
        assertEquals(doctor.getPhoneNumber(), "3331112244");
        assertEquals(doctor.getSpecialization(), Specialization.NEUROLOGIST);
        assertEquals(doctor.toString(), "Angelica Mancini");
        Iterable<Patient> patientIterator = patientRepository.findAll();
        Patient patient = patientIterator.iterator().next();
        assertEquals(patient.getUsername(), "mariorossi");
        assertEquals(patient.getName(), "Mario");
        assertEquals(patient.getSurname(), "Rossi");
        assertEquals(patient.getAddress(), "Via di Esempio 1, Verona");
        assertEquals(patient.getPhoneNumber(), "3331112244");
        assertEquals(patient.toString(), "Mario Rossi");
    }

}
