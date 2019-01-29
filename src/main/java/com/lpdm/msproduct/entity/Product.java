package com.lpdm.msproduct.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="product",schema = "public")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull(message = "le nom ne peut être null")
    private String name;

    @OneToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "la category ne peut être null")
    private Category category;

    @Column
    @NotNull(message = "le label ne peut être null")
    private String label;

    @Column
    @NotNull(message = "le prix ne peut être null")
    @Min(value = 1,message = "le prix ne peut être 0")
    private double price;

    @Column
    @NotNull(message = "la tax ne peut être null")
    private double tax;

    @Column
    @NotNull(message = "le champ deactivate ne peut être null")
    private boolean deactivate;

    @Transient
    private List<Stock> listStock;

    @Column
    @NotNull(message = "la photo ne peut être null")
    private String picture;

    @Column(name="producer_id")
    @JsonIgnore
    private Integer producerID;

    @Transient
    @NotNull(message = "le producteur ne peut être null")
    private Producer producer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public List<Stock> getListStock() {
        return listStock;
    }

    public void setListStock(List<Stock> listStock) {
        this.listStock = listStock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getProducerID() {
        return producerID;
    }

    public void setProducerID(Integer producerID) {
        this.producerID = producerID;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public boolean isDeactivate() {
        return deactivate;
    }

    public void setDeactivate(boolean deactivate) {
        this.deactivate = deactivate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", tax=" + tax +
                ", deactivate=" + deactivate +
                ", listStock=" + listStock +
                ", picture='" + picture + '\'' +
                ", producerID=" + producerID +
                ", producer=" + producer +
                '}';
    }
}
