package com.example.jaz_poprawa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class LogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private String firstDate;
    private String lastDate;
    private LocalDateTime todayDate;

    public LogRecord() {
    }

    public LogRecord(String currency, String firstDate, String lastDate, LocalDateTime todayDate) {
        this.currency = currency;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.todayDate = todayDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public LocalDateTime getCurrentDate() {
        return todayDate;
    }

    public void setCurrentDate(LocalDateTime todayDate) {
        this.todayDate = todayDate;
    }
}