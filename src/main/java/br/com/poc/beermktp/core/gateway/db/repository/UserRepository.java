package br.com.poc.beermktp.core.gateway.db.repository;

import br.com.poc.beermktp.core.gateway.db.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
