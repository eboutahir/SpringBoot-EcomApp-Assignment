package com.CommerceApp.commApp.Model;



import jakarta.persistence.*;
import lombok.Data;



import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false,unique = true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
