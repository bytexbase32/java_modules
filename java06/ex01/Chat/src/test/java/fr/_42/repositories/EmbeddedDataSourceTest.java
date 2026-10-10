package fr._42.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDataSourceTest {

    private EmbeddedDatabase database;
    private DataSource dataSource;

    @BeforeEach
    public void init() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        dataSource = database;
    }

    @Test
    public void dataSourceMustConnectAndLoadProducts() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "SELECT COUNT(*) FROM products")) {

                resultSet.next();
                assertEquals(5, resultSet.getInt(1));
            }
        }
    }

    @AfterEach
    public void shutdown() {
        if (database != null) {
            database.shutdown();
        }
    }
}
