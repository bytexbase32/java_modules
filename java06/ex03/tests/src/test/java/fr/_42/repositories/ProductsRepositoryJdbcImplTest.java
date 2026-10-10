
package fr._42.repositories;

import fr._42.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase database;
    private ProductsRepository repository;

    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS = List.of(
            new Product(1, "pen", 44.50),
            new Product(2, "notebook", 44.50),
            new Product(3, "laptop", 800.00),
            new Product(4, "shirt", 100.00),
            new Product(5, "water", 5.00)
    );

    private final Product EXPECTED_FIND_BY_ID_PRODUCT =
            new Product(1, "pen", 44.50);

    private final Product EXPECTED_UPDATED_PRODUCT =
            new Product(1, "updated pen", 50.00);

    @BeforeEach
    void init() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        repository = new ProductsRepositoryJdbcImpl(database);
    }

    @AfterEach
    void shutdown() {
        if (database != null) {
            database.shutdown();
        }
    }

    @Test
    void findAllReturnsAllProducts() {
        List<Product> products = repository.findAll();

        assertEquals(5, products.size());
        for (int i = 0; i < products.size(); i++) {
            assertProductEquals(
                    EXPECTED_FIND_ALL_PRODUCTS.get(i),
                    products.get(i)
            );
        }
    }

    @Test
    void findByIdReturnsProductWhenItExists() {
        Optional<Product> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertProductEquals(EXPECTED_FIND_BY_ID_PRODUCT, result.get());
    }

    @Test
    void findByIdReturnsEmptyWhenProductDoesNotExist() {
        assertTrue(repository.findById(999L).isEmpty());
    }

    @Test
    void updateChangesExistingProduct() {
        repository.update(EXPECTED_UPDATED_PRODUCT);

        Optional<Product> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertProductEquals(EXPECTED_UPDATED_PRODUCT, result.get());
    }

    @Test
    void saveInsertsNewProduct() {
        Product newProduct = new Product(0, "keyboard", 250.00);

        repository.save(newProduct);

        List<Product> products = repository.findAll();
        assertEquals(6, products.size());

        Product saved = products.get(5);
        assertProductEquals(
                new Product(saved.getId(), "keyboard", 250.00),
                saved
        );
    }

    @Test
    void deleteRemovesProduct() {
        repository.delete(1L);

        assertTrue(repository.findById(1L).isEmpty());
        assertEquals(4, repository.findAll().size());
    }

    private void assertProductEquals(Product expected, Product actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice(), 0.001);
    }
}
