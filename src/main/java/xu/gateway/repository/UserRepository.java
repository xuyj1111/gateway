package xu.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xu.gateway.eneity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);
}