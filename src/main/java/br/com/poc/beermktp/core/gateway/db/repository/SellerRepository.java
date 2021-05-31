package br.com.poc.beermktp.core.gateway.db.repository;

import br.com.poc.beermktp.core.gateway.db.model.Seller;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends PagingAndSortingRepository<Seller, Long> {
    @RestResource(exported = false)
    Optional<Seller> findByCode(String code);
}
