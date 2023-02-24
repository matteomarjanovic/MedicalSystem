package com.matteocompany.medicalsystem.Model;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Doctor {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private Specialization specialization;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Patient> patients;

    public Doctor(String username, String name, String surname, String address, String phoneNumber, Specialization specialization, Set<Patient> patients) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.patients = patients;
    }

    public Doctor(String username, String name, String surname, String address, String phoneNumber, Specialization specialization) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    public Doctor() {}

    public Set<Patient> getPatients() { return patients; }
    public Set<Patient> addPatient(Patient patient) {
        if (this.patients == null) {
            this.patients = new HashSet<Patient>();
            this.patients.add(patient);
            return this.patients;
        }
        this.patients.add(patient);
        return this.patients;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
