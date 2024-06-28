package com.example.java4.repositories;
import com.example.java4.entities.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
public interface HinhAnhRepository extends JpaRepository<HinhAnh, String> {
    @Query("SELECT h FROM HinhAnh h WHERE h.idCTSP.id = :idCTSP")
    HinhAnh findByIdCTSP(@Param("idCTSP") String idCTSP);
}
