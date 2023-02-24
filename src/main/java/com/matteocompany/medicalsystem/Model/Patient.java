package com.matteocompany.medicalsystem.Model;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Patient {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Doctor currentDoctor;

    public Patient(String username, String name, String surname, String address, String phoneNumber, Doctor currentDoctor) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.currentDoctor = currentDoctor;
    }

    public Patient(String username, String name, String surname, String address, String phoneNumber) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Patient() {}

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    public Long getId() {
        return id;
    }


    public void setCurrentDoctor(Doctor currentDoctor) {
        this.currentDoctor = currentDoctor;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
