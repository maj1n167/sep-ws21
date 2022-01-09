package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserByUserId(int id);
}

