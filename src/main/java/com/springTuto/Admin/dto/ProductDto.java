package com.springTuto.Admin.dto;

import com.springTuto.Admin.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter
public class ProductDto implements Serializable {
     private Long id;
    private  String name;
    private  String description;
    private  Double price;
    private  Integer stock;
    private  Double reduction;
    private  String image;
    private  MultipartFile imageFile;
    private Category category;
    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Double price, Integer stock, Double reduction, String image, MultipartFile imageFile,Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.reduction = reduction;
        this.image = image;
        this.imageFile = imageFile;
        this.category=category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto entity = (ProductDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.stock, entity.stock) &&
                Objects.equals(this.reduction, entity.reduction) &&
                Objects.equals(this.image, entity.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, stock, reduction, image);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "price = " + price + ", " +
                "stock = " + stock + ", " +
                "reduction = " + reduction + ", " +
                "image = " + image + ")";
    }
}
