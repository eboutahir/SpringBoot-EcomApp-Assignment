package com.springTuto.Admin.repositories;




import com.springTuto.Admin.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
