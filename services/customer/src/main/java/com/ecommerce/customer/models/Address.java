package com.ecommerce.customer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;



@Validated
@Entity
@Table(name = "address")
public class Address {
    public Address(String house, String zip) {
        this.house = house;
        this.zip = zip;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String house;
    private String zip;
    public Address() {}
    public void setHouse(String house) {
        this.house = house;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    private Address(AddressBuilder builder) {
        this.house = builder.house;
        this.zip = builder.zip;
    }

    // Getters
    public String getHouse() {
        return house;
    }

    public String getZip() {
        return zip;
    }



    // Builder Class
    public static class AddressBuilder {
        private String house;
        private String zip;

        public AddressBuilder house(String house) {
            this.house = house;
            return this;
        }

        public AddressBuilder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
    public static AddressBuilder builder(){
        return new AddressBuilder();
    }


}
