package be.henallux.janvier.dataAccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.henallux.janvier.dataAccess.entity.AuthorityEntity;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
    
    // Trouver toutes les autorités d'un utilisateur
    List<AuthorityEntity> findByUsername(String username);
    
    // Vérifier si une autorité existe pour un utilisateur donné
    boolean existsByUsernameAndAuthority(String username, String authority);
}


