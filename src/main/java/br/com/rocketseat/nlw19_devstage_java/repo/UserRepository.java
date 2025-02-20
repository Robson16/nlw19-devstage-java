package br.com.rocketseat.nlw19_devstage_java.repo;

import br.com.rocketseat.nlw19_devstage_java.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
