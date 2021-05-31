package br.com.poc.beermktp.core.gateway.db.repository;

import br.com.poc.beermktp.core.gateway.db.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    @RestResource(exported = false)
    Optional<Category> findByCode(String code);

    @RestResource(path = "getBySeller", rel = "getBySeller")
    List<Category> findAllBySellerCodeOrderByNameAsc(String sellerCode);
}
