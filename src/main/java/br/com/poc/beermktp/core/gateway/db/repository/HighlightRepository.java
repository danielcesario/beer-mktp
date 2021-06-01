package br.com.poc.beermktp.core.gateway.db.repository;

import br.com.poc.beermktp.core.gateway.db.model.Highlight;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighlightRepository extends PagingAndSortingRepository<Highlight, Long> {
}
