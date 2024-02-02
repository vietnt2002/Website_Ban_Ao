package com.example.java4.repositories;
import com.example.java4.entities.HDCT;
import com.example.java4.entities.HDCTfull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HDCTfullRepository
        extends JpaRepository<HDCTfull,Integer>
{
        public static final int ACTIVE = 1;
        public static final int INACTIVE = 0;
        public Page<HDCTfull> findByTrangThai(int trangThai, Pageable pageable);
        public List<HDCTfull> findAllByHoaDon_Id(Integer id);
};
