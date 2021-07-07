package br.com.adopt.dto;

import br.com.adopt.entity.Address;

import java.io.Serializable;

public class AddressDTO implements Serializable {

    private Long id;
    private String district;
    private String city;
    private String zipCode;
    private String street;
    private String complement;
    private String number;

    public AddressDTO() {}

    public AddressDTO(Long id, String district, String city, String zipCode, String street, String complement, String number) {
        this.id = id;
        this.district = district;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.complement = complement;
        this.number = number;
    }

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
        this.street = address.getStreet();
        this.complement = address.getComplement();
        this.number = address.getNumber();
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
}
