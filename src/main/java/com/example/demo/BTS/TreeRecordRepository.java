package com.example.demo.BTS;

import com.example.demo.BTS.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreeRecordRepository extends JpaRepository<TreeRecord, Long> {
    List<TreeRecord> findAll();
}
