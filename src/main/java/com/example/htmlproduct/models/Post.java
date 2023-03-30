package com.example.htmlproduct.models;


//import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Locale;

public class Post {


    private float money;
    private float crypto;


    public Post(){

    }
    public Post(float money, float crypto) {
        this.money = money; this.crypto = crypto;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public float getCrypto() {
        return crypto;
    }

    public void setCrypto(int crypto) {
        this.crypto = crypto;
    }
}
