package com.matteocompany.medicalsystem.UnitTest;

import com.matteocompany.medicalsystem.Controller.MedicalSystemApplication;
import com.matteocompany.medicalsystem.Model.Doctor;
import com.matteocompany.medicalsystem.Model.Patient;
import com.matteocompany.medicalsystem.Model.Specialization;
import com.matteocompany.medicalsystem.Repository.DoctorRepository;
import com.matteocompany.medicalsystem.Repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicalSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PatientTest {
    @Autowired
    protected PatientRepository patientRepository;
    @Autowired
    protected DoctorRepository doctorRepository;
    @Test
    public void testAddRepository() {
        Patient patient = patientRepository.save(new Patient("user", "name", "surname", "address", "phone"));
        Optional<Patient> patientFound = patientRepository.findById(patient.getId());
        assertTrue(patientFound.isPresent());
    }

    @Test
    public void testRemoveRepository() {
        Patient patient = patientRepository.save(new Patient("user", "name", "surname", "address", "phone"));
        patientRepository.deleteAll();
        Optional<Patient> patientFound = patientRepository.findById(patient.getId());
        assertFalse(patientFound.isPresent());
    }

    @Test
    public void testAddDoctor() {
        Doctor doctor = new Doctor("userdoctor", "namedoctor", "surnamedoctor", "addressdoctor", "phonedoctor", Specialization.CARDIOLOGIST);
        Patient patient = patientRepository.save(new Patient("user", "name", "surname", "address", "phone"));
        doctorRepository.save(doctor);
        patient.setCurrentDoctor(doctor);
        patientRepository.save(patient);
        Optional<Patient> patientFound = patientRepository.findById(patient.getId());
        Doctor addedDoctor = patientFound.get().getCurrentDoctor();
        assertEquals(doctor.getId(), addedDoctor.getId());
    }

    @Test
    public void dummy() {

    }
}
