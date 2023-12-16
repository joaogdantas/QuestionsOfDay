import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.List

@Configuration
class OpenAPIConfig {
    @Value("\${bezkoder.openapi.dev-url}")
    private val devUrl: String? = null
    @Bean
    fun myOpenAPI(): OpenAPI {
        val devServer = Server()
        devServer.url = devUrl
        devServer.description = "Server URL in Development environment"
        val contact = Contact()
        contact.email = "joaogdantasdev@hotmail.com"
        contact.name = "João Gabriel"
        contact.url = "https://www.linkedin.com/in/joaogdantas/"
        val License = License().name("Licença - João Gabriel").url("https://www.linkedin.com/in/joaogdantas/")
        val info = Info()
            .title("QuestionsOfDay")
            .version("0.0.1")
            .contact(contact)
            .description("").termsOfService("https://github.com/joaogdantas/QuestionsOfDay")
            .license(License)
        return OpenAPI().info(info).servers(List.of(devServer))
    }
}