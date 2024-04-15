package jp.webengineer.bookssearch.controller

import jp.webengineer.bookssearch.getTestContainer
import jp.webengineer.bookssearch.migrateFlywayOnBeforeSpec
import jp.webengineer.bookssearch.setDbProperty
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
internal class AuthorsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Add Author`() {
        val requestBuilder = MockMvcRequestBuilders
            .post("/authors")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{"name": "テストユーザ","birthDay": "1950-03-01","comment": "著者情報を作成するテスト"}""")

        mockMvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("テストユーザ"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.birthDay").value("1950-03-01"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("著者情報を作成するテスト"))
    }

    companion object {
        @Container
        val container = getTestContainer()

        init {
            container.start()
        }

        @JvmStatic
        @BeforeAll
        fun migrateFlywayOnBeforeSpec() {
            migrateFlywayOnBeforeSpec(container)
        }

        @JvmStatic
        @DynamicPropertySource
        fun setDbProperty(registry: DynamicPropertyRegistry) {
            setDbProperty(registry, container)
        }
    }
}
