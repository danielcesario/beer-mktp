package br.com.poc.beermktp.core.gateway.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_product", uniqueConstraints = @UniqueConstraint(name = "uk_product__sku", columnNames = {"sku"}))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sku;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal value;
}
