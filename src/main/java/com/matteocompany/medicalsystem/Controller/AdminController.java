package com.matteocompany.medicalsystem.Controller;

import com.matteocompany.medicalsystem.Model.Doctor;
import com.matteocompany.medicalsystem.Model.Patient;
import com.matteocompany.medicalsystem.Model.Specialization;
import com.matteocompany.medicalsystem.Repository.DBInitializer;
import com.matteocompany.medicalsystem.Repository.DoctorRepository;
import com.matteocompany.medicalsystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DBInitializer initializer;

    @RequestMapping("/admin")
    public String admin(Model model){
        initializer.initialize();
        List<Patient> patientData = new LinkedList<>();
        for (Patient u: patientRepository.findAll()){
            patientData.add(u);
        }
        model.addAttribute("patients", patientData);
        List<Doctor> doctorData = new LinkedList<>();
        for (Doctor u: doctorRepository.findAll()){
            doctorData.add(u);
        }
        model.addAttribute("doctors", doctorData);
        return "admin";
    }
    @RequestMapping("/newpatient")
    public String newPatient(Model model){
        model.addAttribute("errorEmptyField", false);
        model.addAttribute("errorUsernameTaken", false);
        return "newpatient";
    }
    @RequestMapping("/createpatient")
    public String createPatient(
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="name", required=true) String name,
            @RequestParam(name="surname", required=true) String surname,
            @RequestParam(name="address", required=true) String address,
            @RequestParam(name="phoneNumber", required=true) String phoneNumber,
            Model model) {

        if (username.isEmpty()
                || name.isEmpty()
                || surname.isEmpty()
                || address.isEmpty()
                || phoneNumber.isEmpty()) {
            model.addAttribute("errorEmptyField", true);
            return "newpatient";
        }
        if (Optional.ofNullable(doctorRepository.findByUsername(username)).isPresent()
                || Optional.ofNullable(patientRepository.findByUsername(username)).isPresent()) {
            model.addAttribute("errorUsernameTaken", true);
            return "newpatient";
        }

        patientRepository.save(new Patient(username, name, surname, address, phoneNumber));
        return "redirect:/admin";
    }
    @RequestMapping("/newdoctor")
    public String newDoctor(Model model){
        model.addAttribute("errorEmptyField", false);
        model.addAttribute("errorUsernameTaken", false);
        return "newdoctor";
    }

    @RequestMapping("/createdoctor")
    public String createDoctor(
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="name", required=true) String name,
            @RequestParam(name="surname", required=true) String surname,
            @RequestParam(name="address", required=true) String address,
            @RequestParam(name="phoneNumber", required=true) String phoneNumber,
            @RequestParam(name="specialization", required=true) Specialization specialization,
            Model model) {

        if (username.isEmpty()
            || name.isEmpty()
            || surname.isEmpty()
            || address.isEmpty()
            || phoneNumber.isEmpty()) {
            model.addAttribute("errorEmptyField", true);
            return "newdoctor";
        }
        if (Optional.ofNullable(doctorRepository.findByUsername(username)).isPresent()
            || Optional.ofNullable(patientRepository.findByUsername(username)).isPresent()) {
            model.addAttribute("errorUsernameTaken", true);
            return "newdoctor";
        }
        doctorRepository.save(new Doctor(username, name, surname, address, phoneNumber, specialization));
        return "redirect:/admin";

    }

    @RequestMapping("/addcurrentdoctor")
    public String addCurrentDoctor(
            @RequestParam(name="username", required=true) String username,
            Model model) {
        System.out.println(username);
        List<Doctor> doctorData = new LinkedList<>();
        for (Doctor u: doctorRepository.findAll()){
            doctorData.add(u);
        }
        model.addAttribute("patient", this.patientRepository.findByUsername(username));
        model.addAttribute("doctors", doctorData);
        return "addcurrentdoctor";
    }

    @RequestMapping("/createdoctormatch")
    public String createDoctorMatch(
            @RequestParam(name="doctor", required=true) String doctorUsername,
            @RequestParam(name="patient", required=true) String patientUsername) {
        Doctor doctor = doctorRepository.findByUsername(doctorUsername);
        Patient patient = patientRepository.findByUsername((patientUsername));
        doctor.addPatient(patient);
        patient.setCurrentDoctor(doctor);
        patientRepository.save(patient);
        doctorRepository.save((doctor));
        System.out.println(patient.getCurrentDoctor());
        return "redirect:/admin";
    }

}
