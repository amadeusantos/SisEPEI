package br.upe.sisepei.core.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "Enter a name")
    private String name;

    @Email(regexp = "^[\\w|\\.]+@upe.br", message = "Invalid email! Please insert a valid institutional email")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Short password")
    private String password;

}
