package br.com.adopt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    private String cpf;

    private String rg;

    private String cnpj;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type_gender")
    private EnumGender typeGender;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type_person")
    private EnumPersonType typePerson;

    @Enumerated(EnumType.ORDINAL)
    private EnumStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_person_donation", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "donation_id"))
    private Set<Donation> donations = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_person_address", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> address = new HashSet<>();

    @Column(name = "created_at")
    private Calendar createdAt;

    public Person() {
    }

    public Person(Long id, User user, String name, String mobilePhone, String cpf, String rg, String cnpj,
                  EnumGender typeGender, EnumPersonType typePerson, Set<Donation> donations, Set<Address> address, EnumStatus status) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.cpf = cpf;
        this.rg = rg;
        this.cnpj = cnpj;
        this.typeGender = typeGender;
        this.typePerson = typePerson;
        this.donations = donations;
        this.address = address;
        this.status = status;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public EnumGender getTypeGender() {
        return typeGender;
    }

    public void setTypeGender(EnumGender typeGender) {
        this.typeGender = typeGender;
    }

    public EnumPersonType getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(EnumPersonType typePerson) {
        this.typePerson = typePerson;
    }

    public Set<Donation> getDonations() {
        return donations;
    }

    public void setDonations(Set<Donation> donations) {
        this.donations = donations;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Calendar getCreatedAt() {return createdAt;}

    public void setCreatedAt(Calendar createdAt) {this.createdAt = createdAt;}

    public EnumStatus getStatus() {return status;}

    public void setStatus(EnumStatus status) {this.status = status;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
