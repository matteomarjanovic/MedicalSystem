package com.matteocompany.medicalsystem.Repository;

import com.matteocompany.medicalsystem.Model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Patient findByUsername(String username);
    Optional<Patient> findById(Long id);

}
