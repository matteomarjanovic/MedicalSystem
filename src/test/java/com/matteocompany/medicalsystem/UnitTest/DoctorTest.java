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

import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicalSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DoctorTest {
    @Autowired
    protected PatientRepository patientRepository;
    @Autowired
    protected DoctorRepository doctorRepository;
    @Test
    public void testAddRepository() {
        Doctor doctor = doctorRepository.save(new Doctor("user", "name", "surname", "address", "phone", Specialization.NEUROLOGIST));
        Optional<Doctor> doctorFound = doctorRepository.findById(doctor.getId());
        assertTrue(doctorFound.isPresent());
    }

    @Test
    public void testRemoveRepository() {
        Doctor doctor = doctorRepository.save(new Doctor("user", "name", "surname", "address", "phone", Specialization.RADIOLOGIST));
        doctorRepository.delete(doctor);
        Optional<Doctor> doctorFound = doctorRepository.findById(doctor.getId());
        assertFalse(doctorFound.isPresent());
    }

    @Test
    public void testRemoveDoctor() {
        Doctor doctor = new Doctor("userdoctor", "namedoctor", "surnamedoctor", "addressdoctor", "phonedoctor", Specialization.CARDIOLOGIST);
        Patient patient1 = patientRepository.save(new Patient("user1", "name1", "surname1", "address1", "phone1"));
        Patient patient2 = patientRepository.save(new Patient("user2", "name2", "surname2", "address2", "phone2"));
        doctorRepository.save(doctor);
        patient1.setCurrentDoctor(doctor);
        patient2.setCurrentDoctor(doctor);
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        doctor.addPatient(patient1);
        doctor.addPatient(patient2);
        doctorRepository.save(doctor);

        if (!doctor.getPatients().isEmpty()) {
            Iterator<Patient> patientsIterator = doctor.getPatients().iterator();
            while (patientsIterator.hasNext()) {
                Patient patient = patientsIterator.next();
                patient.setCurrentDoctor(null);
                patientRepository.save(patient);
            }
            doctor.setPatients(null);
            doctorRepository.save(doctor);
        }
        doctorRepository.delete(doctor);
        assertFalse(doctorRepository.existsById(doctor.getId()));
    }

}
