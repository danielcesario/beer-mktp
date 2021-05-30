package br.com.poc.beermktp.core.config;

import br.com.poc.beermktp.core.gateway.db.model.Category;
import br.com.poc.beermktp.core.gateway.db.model.Product;
import br.com.poc.beermktp.core.gateway.db.model.Seller;
import br.com.poc.beermktp.core.gateway.db.repository.CategoryRepository;
import br.com.poc.beermktp.core.gateway.db.repository.ProductRepository;
import br.com.poc.beermktp.core.gateway.db.repository.SellerRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CreateData implements CommandLineRunner {

    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CreateData(SellerRepository sellerRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        buildSellers();
        buildCategories();
        buildProducts();
    }

    private void buildSellers() throws Exception {
        List<List<String>> records = readCSV("sellers.csv");
        records.stream()
                .skip(1)
                .forEach(record -> {
                    sellerRepository.save(Seller.builder().name(record.get(0)).code(record.get(1)).logo(record.get(2)).cashback(new BigDecimal(record.get(3))).build());
                });
    }

    private void buildCategories() throws Exception {
        List<List<String>> records = readCSV("categories.csv");
        records.stream()
                .skip(1)
                .forEach(record -> {
                    Seller seller = sellerRepository.findByCode(record.get(0)).orElseThrow(() -> new RuntimeException("Seller not found"));
                    categoryRepository.save(Category.builder().seller(seller).name(record.get(1)).code(record.get(2)).build());
                });
    }

    private void buildProducts() throws Exception {
        List<List<String>> records = readCSV("products.csv");
        records.stream()
                .skip(1)
                .forEach(record -> {
                    Category category = categoryRepository.findByCode(record.get(0)).orElseThrow(() -> new RuntimeException("Category not found"));
                    productRepository.save(Product.builder()
                            .category(category)
                            .sku(record.get(1))
                            .value(new BigDecimal(record.get(2)))
                            .name(record.get(3))
                            .imageUrl(record.get(4))
                            .description(record.get(5))
                            .build());
                });
    }

    private List<List<String>> readCSV(String fileName) throws Exception {
        URL resource = getClass().getClassLoader().getResource(fileName);
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(resource.toURI().getPath()))){
            String[] values = null;
            while((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

}
