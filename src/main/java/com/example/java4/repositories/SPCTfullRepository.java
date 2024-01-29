package com.example.java4.repositories;
import com.example.java4.entities.SPCT;
import com.example.java4.entities.SPCTfull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPCTfullRepository
        extends JpaRepository<SPCTfull,Integer>
{

};
