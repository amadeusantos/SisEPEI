package br.upe.sisepei.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "SisPEI - UPE", email = "amadeu.santos@upe.br", url = "https://github.com/amadeusantos/SisEPEI"), description = "Sistems de Editais de Pesquisa, Extensão e Inovação.", title = "SisEPEI", version = "1.0", license = @License(name = "Licença da API", url = "https://www.gnu.org/licenses/gpl-3.0.html"), termsOfService = "Termos de serviço"), servers = {
        @Server(description = "Local ENV", url = "http://localhost:8080"), @Server(description = "Server ENV", url = "http://0.0.0.0:8080")}, security = {
        @SecurityRequirement(name = "bearerAuth")})
@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}

