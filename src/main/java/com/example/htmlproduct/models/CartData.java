package com.example.htmlproduct.models;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;

@Entity
public class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number, holders, month, year, cvv;
    private BigDecimal money;

    public CartData() {
    }

    public CartData(String number, String holders, String month, String year, String cvv, BigDecimal money) {
        this.number = number;
        this.holders = holders;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHolders() {
        return holders;
    }

    public void setHolders(String holders) {
        this.holders = holders;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
