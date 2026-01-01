package be.henallux.janvier.dataAccess.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.henallux.janvier.dataAccess.entity.AuthorityEntity;
import be.henallux.janvier.dataAccess.entity.UserEntity;
import be.henallux.janvier.dataAccess.repository.AuthorityRepository;
import be.henallux.janvier.dataAccess.repository.UserRepository;

@Service
@Transactional
@SuppressWarnings("null")
public class UserDAO {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserDAO(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void saveAuthority(AuthorityEntity authorityEntity) {
        authorityRepository.save(authorityEntity);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
