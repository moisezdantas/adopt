package br.com.adopt.dto;

import br.com.adopt.entity.Person;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private UserDTO user;

    private String name;

    private String mobilePhone;

    private String cpf;
    private String rg;
    private String cnpj;

    private Integer typeGender;
    private Integer typePerson;
    private Integer status;

    private Set<AddressDTO> address = new HashSet<>();

    public PersonDTO() {}

    public PersonDTO(Long id, UserDTO user, String name, String mobilePhone, String cpf, String rg, String cnpj, Integer typeGender, Integer typePerson, Set<AddressDTO> address, Integer status) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.cpf = cpf;
        this.rg = rg;
        this.cnpj = cnpj;
        this.typeGender = typeGender;
        this.typePerson = typePerson;
        this.address = address;
        this.status = status;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.user = new UserDTO(person.getUser());
        this.name = person.getName();
        this.mobilePhone = person.getMobilePhone();
        this.cpf = person.getCpf();
        this.rg = person.getRg();
        this.cnpj = person.getCnpj();
        this.typeGender = person.getTypeGender().ordinal();
        this.typePerson = person.getTypePerson() != null ? person.getTypePerson().ordinal() : null;
        this.status = person.getStatus().ordinal();
        person.getAddress().forEach(address -> this.getAddress().add(new AddressDTO(address)));
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getTypeGender() {
        return typeGender;
    }

    public void setTypeGender(Integer typeGender) {
        this.typeGender = typeGender;
    }

    public Integer getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(Integer typePerson) {
        this.typePerson = typePerson;
    }

    public Set<AddressDTO> getAddress() {
        return address;
    }

    public Integer getStatus() {return status;}

    public void setStatus(Integer status) {this.status = status;}

}
