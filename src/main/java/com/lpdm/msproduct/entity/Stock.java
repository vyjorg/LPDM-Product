package com.lpdm.msproduct.entity;


import java.time.LocalDate;

public class Stock {

    private Integer id;
    private Integer quantity;
    private LocalDate expireDate;
    private String packaging;
    private Integer unitByPackage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public Integer getUnitByPackage() {
        return unitByPackage;
    }

    public void setUnitByPackage(Integer unitByPackage) {
        this.unitByPackage = unitByPackage;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", expireDate=" + expireDate +
                ", packaging='" + packaging + '\'' +
                ", unitByPackage=" + unitByPackage +
                '}';
    }
}
