package be.henallux.janvier.dataAccess.repository;

import be.henallux.janvier.dataAccess.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
    
    // Trouver toutes les autorités d'un utilisateur
    List<AuthorityEntity> findByUsername(String username);
}


