package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IHinhAnhRepository extends JpaRepository<HinhAnh,String> {

    @Query("SELECT h FROM HinhAnh h WHERE h.idCTSP.id = :idCTSP")
    HinhAnh findByIdCTSP(@Param("idCTSP") String idCTSP);
}
