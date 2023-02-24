package com.matteocompany.medicalsystem.Controller;

import com.matteocompany.medicalsystem.Repository.DBInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppController {
    @Autowired
    private DBInitializer initializer;

    @RequestMapping("/")
    public String index(){
        initializer.initialize();
        return "welcome";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model){
        initializer.initialize();
        return "welcome";
    }
    @RequestMapping("/trylogin")
    public String trylogin(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin";
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"))) {
            return "redirect:/patientview";
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"))) {
            return "redirect:/doctorview";
        } else {
            initializer.initialize();
            return "welcome";
        }
    }

    @RequestMapping("/tryloginafterlogout")
    public String tryLoginAfterLogout(Model model){
        return "redirect:/trylogin";
    }

}
