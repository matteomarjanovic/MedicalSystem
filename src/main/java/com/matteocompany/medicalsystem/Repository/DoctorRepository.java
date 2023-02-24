package com.matteocompany.medicalsystem.Repository;

import com.matteocompany.medicalsystem.Model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Doctor findByUsername(String username);

}
