package com.app_ecomerce.Model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String UserEmail;

    private String userPassword;

    private String userPhoneNo;

    @OneToOne
    @JoinColumn(name = "fk_Address_Id")
    private Address address;

    public void setAddress(Address address) {
        this.address=address;
    }
}
/*User Model:
   Id:integer
    name:string
    email:string
   password:string
   phoneNumber:string*/