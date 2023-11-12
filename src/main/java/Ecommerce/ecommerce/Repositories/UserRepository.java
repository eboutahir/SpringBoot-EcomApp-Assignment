package Ecommerce.ecommerce.Repositories;

import Ecommerce.ecommerce.Models.UserM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserM,Integer> {
    Optional<UserM> findByUsername ( String username );
}
