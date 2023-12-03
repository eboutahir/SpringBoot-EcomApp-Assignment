package com.CommerceApp.commApp.Model;






import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    @Column(nullable=false)
    private String firstName;

    private String lastName;
    @NotEmpty
    private String password;
    @Column(nullable = false,unique = true)
    @NotEmpty
     @Email(message="{error.invalid_email}")
    private String email;

    public User(User user) {
        this.id = user.getId();
        this.firstName=user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
    public User()
    {

    }



    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName = "ID")}
            ,inverseJoinColumns={@JoinColumn (name="ROLE_ID",referencedColumnName ="ID" )}
            )
    private List<Role> roles;

}
