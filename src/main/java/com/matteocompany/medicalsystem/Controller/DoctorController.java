package com.matteocompany.medicalsystem.Controller;

import com.matteocompany.medicalsystem.Model.Doctor;
import com.matteocompany.medicalsystem.Model.Patient;
import com.matteocompany.medicalsystem.Repository.DBInitializer;
import com.matteocompany.medicalsystem.Repository.DoctorRepository;
import com.matteocompany.medicalsystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.Optional;

@Controller
public class DoctorController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DBInitializer initializer;
    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @RequestMapping("/doctorview")
    public String doctor(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        model.addAttribute("isAdmin", false);

        Optional<Doctor> result = Optional.ofNullable(doctorRepository.findByUsername(currentUsername));
        if (result.isPresent()) {
            Doctor currentDoctor = result.get();
            model.addAttribute("doctorToDisplay", currentDoctor);
            return "doctorview";
        }
        else {
            return "notfound";
        }
    }
    @RequestMapping("/doctorviewadmin")
    public String doctorViewAdmin(
            @RequestParam(name="username", required=true) String username,
            Model model) {
        model.addAttribute("isAdmin", true);

        Optional<Doctor> result = Optional.ofNullable(doctorRepository.findByUsername(username));
        if (result.isPresent()) {
            Doctor currentDoctor = result.get();
            model.addAttribute("doctorToDisplay", currentDoctor);
            return "doctorview";
        }
        else {
            return "notfound";
        }
    }

    @RequestMapping("/deletedoctor")
    public String modifyPatient(
            @RequestParam(name="username", required = true) String username,
            Model model
    ) {
        Optional<Doctor> result = Optional.ofNullable(doctorRepository.findByUsername(username));
        if (result.isPresent()) {
            Doctor doctorToDelete = result.get();
            if (!doctorToDelete.getPatients().isEmpty()) {
                Iterator<Patient> patientsIterator = doctorToDelete.getPatients().iterator();
                while (patientsIterator.hasNext()) {
                    Patient patient = patientsIterator.next();
                    patient.setCurrentDoctor(null);
                    patientRepository.save(patient);
                }
                doctorToDelete.setPatients(null);
                doctorRepository.save(doctorToDelete);
            }
            doctorRepository.delete(doctorToDelete);
            inMemoryUserDetailsManager.deleteUser(username);
            return "redirect:/admin";
        }
        else {
            return "notfound";
        }

    }



}
