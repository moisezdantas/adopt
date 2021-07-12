package br.com.adopt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tb_animal")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String rga; // Registro geral do animal

    @Column(name = "birth_date")
    private String birthDate;

    private String deficiency;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "is_vaccinated")
    private EnumVaccinated isVaccinated;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type_animal_gender")
    private EnumTypeAnimalGender typeAnimalGender;

    @Column(name = "is_castrated")
    private Boolean isCastrated; // castrado

    private Integer year;

    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "created_at")
    private Calendar createdAt;

    @Column(name= "image_url")
    private String imageUrl;

    public Animal() {
    }

    public Animal(Long id, String name, String rga, String birthDate, String deficiency, EnumVaccinated isVaccinated, EnumTypeAnimalGender typeAnimalGender, Boolean isCastrated, Integer year, String note, Breed breed, Address address, String imageUrl) {
        this.id = id;
        this.name = name;
        this.rga = rga;
        this.birthDate = birthDate;
        this.deficiency = deficiency;
        this.isVaccinated = isVaccinated;
        this.typeAnimalGender = typeAnimalGender;
        this.isCastrated = isCastrated;
        this.year = year;
        this.note = note;
        this.breed = breed;
        this.address = address;
        this.imageUrl = imageUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRga() {
        return rga;
    }

    public void setRga(String rga) {
        this.rga = rga;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(String deficiency) {
        this.deficiency = deficiency;
    }

    public EnumVaccinated getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(EnumVaccinated isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public EnumTypeAnimalGender getTypeAnimalGender() {
        return typeAnimalGender;
    }

    public void setTypeAnimalGender(EnumTypeAnimalGender typeAnimalGender) {
        this.typeAnimalGender = typeAnimalGender;
    }

    public Boolean isCastrated() {
        return isCastrated;
    }

    public void setIsCastrated(Boolean isCastrated) {
        this.isCastrated = isCastrated;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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
        Animal other = (Animal) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
