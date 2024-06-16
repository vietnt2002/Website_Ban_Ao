package com.example.java4.repositories;
import com.example.java4.entities.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
public interface HinhAnhRepository extends JpaRepository<HinhAnh, String> {
    @Query("select ha from HinhAnh ha where ha.idCTSP.id = ?1")
    HinhAnh findByIdCTSP(String idCTSP);
}
