package br.com.projuris.profeed.repository;

import br.com.projuris.profeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByRolesIsNotContainingAndUsernameNot(List<String> rolesExcluded, String username );
}
