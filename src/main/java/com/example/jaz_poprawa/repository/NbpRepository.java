package com.example.jaz_poprawa.repository;

import com.example.jaz_poprawa.model.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NbpRepository extends JpaRepository<LogRecord, Long> {
}