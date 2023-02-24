package com.matteocompany.medicalsystem.Controller;

import com.matteocompany.medicalsystem.Model.Doctor;
import com.matteocompany.medicalsystem.Model.Patient;
import com.matteocompany.medicalsystem.Repository.DBInitializer;
import com.matteocompany.medicalsystem.Repository.DoctorRepository;
import com.matteocompany.medicalsystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DBInitializer initializer;

    @RequestMapping("/patientview")
    public String patientView(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        model.addAttribute("isAdmin", false);

        Optional<Patient> result = Optional.ofNullable(patientRepository.findByUsername(currentUsername));
        if (result.isPresent()) {
            Patient currentPatient = result.get();
            model.addAttribute("patientToDisplay", currentPatient);
            return "patientview";
        }
        else {
            return "notfound";
        }
    }

    @RequestMapping("/patientviewadmin")
    public String patientViewAdmin(
            @RequestParam(name="username", required=true) String username,
            Model model){
        model.addAttribute("isAdmin", true);

        Optional<Patient> result = Optional.ofNullable(patientRepository.findByUsername(username));
        if (result.isPresent()) {
            Patient currentPatient = result.get();
            model.addAttribute("patientToDisplay", currentPatient);
            return "patientview";
        }
        else {
            return "notfound";
        }
    }

    @RequestMapping("/patientedit")
    public String patientedit(
            @RequestParam(name="username", required=true) String username,
            Model model){

        Optional<Patient> result = Optional.ofNullable(patientRepository.findByUsername(username));
        if (result.isPresent()) {
            Patient currentPatient = result.get();
            model.addAttribute("patientToEdit", currentPatient);
            model.addAttribute("errorEmptyField", false);
            model.addAttribute("errorUsernameTaken", false);
            return "patientedit";
        }
        else {
            return "notfound";
        }
    }

    @RequestMapping("/modifypatient")
    public String modifyPatient(
            @RequestParam(name="patientId", required = true) Long patientId,
            @RequestParam(name="newUsername", required = true) String newUsername,
            @RequestParam(name="newName", required = true) String newName,
            @RequestParam(name="newSurname", required = true) String newSurname,
            @RequestParam(name="newAddress", required = true) String newAddress,
            @RequestParam(name="newPhoneNumber", required = true) String newPhoneNumber,
            Model model
    ) {
        if (newUsername.isEmpty()
                || newName.isEmpty()
                || newSurname.isEmpty()
                || newAddress.isEmpty()
                || newPhoneNumber.isEmpty()) {

            Optional<Patient> result = patientRepository.findById(patientId);
            if (result.isPresent()) {
                Patient currentPatient = result.get();
                model.addAttribute("patientToEdit", currentPatient);
                model.addAttribute("errorEmptyField", true);
                return "patientedit";
            }
            else {
                return "notfound";
            }
        }
        Optional<Doctor> resultDoctor = Optional.ofNullable(doctorRepository.findByUsername(newUsername));
        Optional<Patient> resultPatient = Optional.ofNullable(patientRepository.findByUsername(newUsername));
        if ((resultPatient.isPresent() && resultPatient.get().getId() != patientId)
                || resultDoctor.isPresent()) {
            Optional<Patient> result = patientRepository.findById(patientId);
            if (result.isPresent()) {
                Patient currentPatient = result.get();
                model.addAttribute("patientToEdit", currentPatient);
                model.addAttribute("errorUsernameTaken", true);
                return "patientedit";
            }
            else {
                return "notfound";
            }
        }
        Optional<Patient> result = patientRepository.findById(patientId);
        if (result.isPresent()) {
            Patient patientToModify = result.get();
            patientToModify.setUsername(newUsername);
            patientToModify.setName(newName);
            patientToModify.setSurname(newSurname);
            patientToModify.setAddress(newAddress);
            patientToModify.setPhoneNumber(newPhoneNumber);
            patientRepository.save(patientToModify);
            return "redirect:/admin";
        }
        else {
            return "notfound";
        }

    }

}
