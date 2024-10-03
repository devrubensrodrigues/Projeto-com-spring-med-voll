package med.voll.api.consulta;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.Paciente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tb_consultas")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    private LocalDateTime dataHora;

    public Consulta(Paciente paciente, Medico medico, String dataHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = formatarDataHora(dataHora);
    }

    private LocalDateTime formatarDataHora(String dataHora) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return LocalDateTime.parse(dataHora, inputFormatter);
    }
}
