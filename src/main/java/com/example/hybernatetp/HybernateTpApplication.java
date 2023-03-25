package com.example.hybernatetp;

import com.example.hybernatetp.Entities.Patient;
import com.example.hybernatetp.Entities.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class HybernateTpApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HybernateTpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for(int i = 0;i<100;i++) {
            patientRepository.save(new Patient(i, "Nasr"+i, new Date(22 / 11 / 2022), Math.random()>0.5?true:false, (int)Math.random()*100+i));
        }
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(1, 5)); //To show a limited number of element from DB
        System.out.println("Total elements and pages: "+patients.getTotalElements() + " "+ patients.getTotalPages() );
        patients.forEach(patient -> {
            System.out.println("----------------------------");
            System.out.println("Name: "+patient.getNom());
            System.out.println("Date: "+patient.getDateNaissance());
            System.out.println("Score: "+patient.getScore());
        });


       // Optional<Patient> patient = patientRepository.findById(Long.valueOf(2));
       Page<Patient> patientsMalade = patientRepository.findByMaladeAndScoreLessThan(true,40, PageRequest.of(1,15));
        List<Patient> patientList = patientRepository.findPatients("%r2%",80);
        }
}
