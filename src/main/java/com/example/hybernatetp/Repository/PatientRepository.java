package com.example.hybernatetp.Repository;

import com.example.hybernatetp.Entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByMaladeAndScoreLessThan(boolean malade, int score, Pageable pageable);

    @Query("select p from Patient p where p.nom like :z and p.score<:y")
    List<Patient> findPatients(@Param("z") String name , @Param("y")int score);
    Page<Patient> findByNomContains(String keyW, Pageable page);

}
