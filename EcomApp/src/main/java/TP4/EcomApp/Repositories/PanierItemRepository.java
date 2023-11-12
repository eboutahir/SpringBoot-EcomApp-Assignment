package TP4.EcomApp.Repositories;

import TP4.EcomApp.entity.Panier;
import TP4.EcomApp.entity.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierItemRepository extends JpaRepository<PanierItem,Long>
{
    List<PanierItem> findByPanier(Panier panier);
}
