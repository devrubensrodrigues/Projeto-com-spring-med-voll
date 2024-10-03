package med.voll.api.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroConsulta(@NotNull
                                    Long pacienteId,
                                    @NotNull
                                    Long medicoId,
                                    @NotBlank
                                    String dataHora) {
}
