package com.example.test;

import java.util.Objects;

public class OwnerModel {

    private final Long id;
    private final String fio;
    private final String email;
    private final String telephone;


    public OwnerModel(Long id, String fio, String email, String telephone) {
        this.id = id;
        this.fio = fio;
        this.email = email;
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerModel that = (OwnerModel) o;
        return Objects.equals(id, that.id) && Objects.equals(fio, that.fio) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, email, telephone);
    }

    @Override
    public String toString() {
        return "OwnerModel{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }
}
