package Ecomerce.springTuto.account.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column( length = 30, nullable = false)
    private String firstName;
    @Column( length = 30, nullable = false)
    private String lasttName;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", length = 80, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public User() {

    }

    public User(String firstName, String lasttName, String password, String email, Set<Role> roles) {
        this.firstName = firstName;
        this.lasttName = lasttName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return getId() == that.getId() && getFirstName().equals(that.getFirstName()) && getLasttName().equals(that.getLasttName()) && getPassword().equals(that.getPassword()) && getEmail().equals(that.getEmail()) && getRoles().equals(that.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLasttName(), getPassword(), getEmail(), getRoles());
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lasttName='" + lasttName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
