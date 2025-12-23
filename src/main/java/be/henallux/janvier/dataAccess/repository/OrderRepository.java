package be.henallux.janvier.dataAccess.repository;

import be.henallux.janvier.dataAccess.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    
    // Trouver toutes les commandes d'un utilisateur
    List<OrderEntity> findByUserIdOrderByDateCommandeDesc(Integer userId);
    
    // Trouver les commandes payées
    List<OrderEntity> findByPaye(Boolean paye);
}


