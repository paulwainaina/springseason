package com.hapticsolutions.mooland;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("COW")
public class Cow {
    @Id
    private Integer id;
    @NotEmpty
    private String name;
    private String passport;
    @JsonIgnore
    private byte[] data;
    public Cow(Integer id,String name,String passport){
        this.id=id;
        this.name=name;
        this.passport=passport;
    }

    public Cow(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
