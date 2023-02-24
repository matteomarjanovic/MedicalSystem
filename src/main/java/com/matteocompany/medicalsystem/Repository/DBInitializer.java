package com.matteocompany.medicalsystem.Repository;

import com.matteocompany.medicalsystem.Model.Admin;
import com.matteocompany.medicalsystem.Model.Doctor;
import com.matteocompany.medicalsystem.Model.Patient;
import com.matteocompany.medicalsystem.Model.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class DBInitializer {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private Boolean initialized;

    public DBInitializer() {
        this.initialized = false;
    }

    public void initialize() {
        if (this.initialized == false) {
            patientRepository.save(new Patient("mariorossi", "Mario", "Rossi", "Via di Esempio 1, Verona", "3331112244"));
            UserDetails usr = User.withUsername("mariorossi").password("password").roles("PATIENT").build();
            inMemoryUserDetailsManager.createUser(usr);
            patientRepository.save(new Patient("marcobianchi", "Marco", "Bianchi", "Via di Esempio 2, Verona", "3331112244"));
            usr = User.withUsername("marcobianchi").password("password").roles("PATIENT").build();
            inMemoryUserDetailsManager.createUser(usr);
            patientRepository.save(new Patient("rosaferri", "Rosa", "Ferri", "Via di Esempio 3, Verona", "3331112244"));
            usr = User.withUsername("rosaferri").password("password").roles("PATIENT").build();
            inMemoryUserDetailsManager.createUser(usr);
            patientRepository.save(new Patient("enricopiazza", "Enrico", "Piazza", "Via di Esempio 4, Verona", "3331112244"));
            usr = User.withUsername("enricopiazza").password("password").roles("PATIENT").build();
            inMemoryUserDetailsManager.createUser(usr);
            patientRepository.save(new Patient("angelamanna", "Angela", "Manna", "Via di Esempio 5, Verona", "3331112244"));
            usr = User.withUsername("angelamanna").password("password").roles("PATIENT").build();
            inMemoryUserDetailsManager.createUser(usr);
            patientRepository.save(new Patient("luisarusso", "Luisa", "Russo", "Via di Esempio 6, Verona", "3331112244"));
            usr = User.withUsername("luisarusso").password("password").roles("PATIENT").build();
            inMemoryUserDetailsManager.createUser(usr);
            patientRepository.save(new Patient("albertopalermo", "Alberto", "Palermo", "Via di Esempio 7, Verona", "3331112244"));
            usr = User.withUsername("albertopalermo").password("password").roles("PATIENT").build();
            inMemoryUserDetailsManager.createUser(usr);

            doctorRepository.save(new Doctor("angelicamancini", "Angelica", "Mancini", "Via di Esempio 8, Verona", "3331112244", Specialization.NEUROLOGIST));
            usr = User.withUsername("angelicamancini").password("password").roles("DOCTOR").build();
            inMemoryUserDetailsManager.createUser(usr);
            doctorRepository.save(new Doctor("vincenzotrevisano", "Vincenzo", "Trevisano" , "Via di Esempio 9, Verona", "3331112244", Specialization.CARDIOLOGIST));
            usr = User.withUsername("vincenzotrevisano").password("password").roles("DOCTOR").build();
            inMemoryUserDetailsManager.createUser(usr);
            doctorRepository.save(new Doctor("giudittatrentino", "Giuditta", "Trentino", "Via di Esempio 10, Verona", "3331112244", Specialization.PEDIATRICIAN));
            usr = User.withUsername("giudittatrentino").password("password").roles("DOCTOR").build();
            inMemoryUserDetailsManager.createUser(usr);

            adminRepository.save(new Admin("admin", "admin", "admin", "admin"));

            this.initialized = true;
        }
    }

}
