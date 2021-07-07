package br.com.adopt.entity;

import br.com.adopt.dto.AddressDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;
    private String city;
    private String zipCode;
    private String street;

    private String complement;
    private String number;

    @Column(name = "created_at")
    private Calendar createdAt;

    public Address() {
    }

    public Address(Long id, String district, String city, String zipCode, String street, String complement, String number) {
        this.id = id;
        this.district = district;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.complement = complement;
        this.number = number;
        this.createdAt = Calendar.getInstance();
    }

    public Address(AddressDTO dto) {
        this.id = dto.getId();
        this.district = dto.getDistrict();
        this.city = dto.getCity();
        this.zipCode = dto.getZipCode();
        this.street = dto.getStreet();
        this.complement = dto.getComplement();
        this.number = dto.getNumber();
        this.createdAt = Calendar.getInstance();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @PrePersist
    public void prePersist() {
        createdAt = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id != null ? id.equals(address.id) : address.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
