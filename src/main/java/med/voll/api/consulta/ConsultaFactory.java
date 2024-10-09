package med.voll.api.consulta;

import med.voll.api.medico.Medico;
import med.voll.api.paciente.Paciente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ConsultaFactory {

    private final LocalTime horaInicio = LocalTime.of(7,0);
    private final LocalTime horaTermino = LocalTime.of(19,0);

    public Consulta validarParametrosConsulta(Paciente paciente, Medico medico, DadosCadastroConsulta dados) {
        if (medico.getAtivo() && paciente.isAtivo()) {
            var consulta = new Consulta(paciente, medico, dados.dataHora());
            return validandoHorario(consulta);
        }else {
            throw new IllegalArgumentException("Paciente ou médico inativo!");
        }
    }
    public Consulta validandoHorario(Consulta consulta) {
        if (consulta.getDataHora().getHour() >= horaInicio.getHour() &&
                consulta.getHoraFinal().getHour() <= horaTermino.getHour()) {
            if (consulta.getDataHora().minusMinutes(30).toLocalTime().isAfter(LocalDateTime.now().toLocalTime())) {
                return consulta;
            } else {
                throw new IllegalArgumentException("Consulta precisa ser marcada 30 minutos antes!");
            }
        }else {
            throw new IllegalArgumentException("Fora do horário de funcionamento da clínica!");
        }
    }
}
