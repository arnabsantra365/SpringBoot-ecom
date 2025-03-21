package com.ecommerce.customer.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customers")
@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="add_id",referencedColumnName = "id",nullable = false)
    private Address address;
    @Column(name="password",nullable = false)
    private String password;
    @Transient
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Customer() {}

    public Customer(String firstname, String lastname, String email, Address address,String password, String confirmPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.password=password;
        this.confirmPassword=confirmPassword;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.address=builder.address;
        this.password=builder.password;

    }

    // Static inner Builder class
    public static class CustomerBuilder {
        private Integer id;
        private String firstname;
        private String lastname;
        private String email;
        private Address address;
        private String password;
        public CustomerBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder address(Address address){
            this.address=address;
            return this;
        }
        public CustomerBuilder password(String password){
            this.password=password;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    // Static method to get builder instance
    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

//    public Integer getId() {
//        return Id;
//    }
}
