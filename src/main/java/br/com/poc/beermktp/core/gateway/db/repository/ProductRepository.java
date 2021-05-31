package br.com.poc.beermktp.core.gateway.db.repository;

import br.com.poc.beermktp.core.gateway.db.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @RestResource(path = "getBySku", rel = "getBySku")
    Product findBySku(String sku);

    @RestResource(path = "getByCategory", rel = "getByCategory")
    Page<Product> findAllByCategoryCode(String categoryCode, Pageable pageable);
}
