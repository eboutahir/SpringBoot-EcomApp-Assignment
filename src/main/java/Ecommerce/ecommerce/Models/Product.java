package Ecommerce.ecommerce.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "product")
public class Product {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserM user;
    private int quantity;

    private int price;

    private int weight;

    private String description;
}
