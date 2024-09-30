package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroPaciente(@NotBlank
                                    String nome,
                                    @NotBlank
                                    @Email
                                    String email,
                                    @NotBlank
                                    @Pattern(regexp = "\\d{11}")
                                    String telefone,
                                    @NotBlank
                                    @Pattern(regexp = "\\d{11}")
                                    String cpf,
                                    //Para validar o endereço eu estava usando o NotBlank e por isso estava dando erro
                                    @NotNull
                                    @Valid
                                    DadosEndereco endereco) {
}
