package Ecomerce.springTuto.account.repositories;

import Ecomerce.springTuto.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
