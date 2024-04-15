package jp.webengineer.bookssearch

import org.flywaydb.core.Flyway
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.PostgreSQLContainer

fun getTestContainer(): PostgreSQLContainer<out PostgreSQLContainer<*>> {
    val dbContainer = PostgreSQLContainer<Nothing>("postgres:latest").apply {
        withDatabaseName("mydatabase")
        withUsername("myuser")
        withPassword("secret")
    }
    return dbContainer
}

fun migrateFlywayOnBeforeSpec(container: PostgreSQLContainer<out PostgreSQLContainer<*>>) {
    Flyway.configure()
        .dataSource(
            container.jdbcUrl,
            container.username,
            container.password
        )
        .load()
        .migrate()
}

fun setDbProperty(registry: DynamicPropertyRegistry, dbContainer: PostgreSQLContainer<out PostgreSQLContainer<*>>) {
    registry.add("db.url") { dbContainer.jdbcUrl }
    registry.add("db.user") { dbContainer.username }
    registry.add("db.password") { dbContainer.password }
    registry.add("spring.datasource.url") { dbContainer.jdbcUrl }
    registry.add("spring.datasource.username") { dbContainer.username }
    registry.add("spring.datasource.password") { dbContainer.password }
}