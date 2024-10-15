package med.voll.api.consulta;

import med.voll.api.medico.Medico;
import med.voll.api.paciente.Paciente;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class ConsultaFactory {

    private final LocalTime horaInicio = LocalTime.of(7,0);
    private final LocalTime horaTermino = LocalTime.of(19,0);
    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta validarParametrosConsulta(Paciente paciente, Medico medico, DadosCadastroConsulta dados) {
        if (medico.getAtivo() && paciente.isAtivo()) {
            var consulta = new Consulta(paciente, medico, dados.dataHora());
            return validandoHorario(conferirAgendamentoPaciente(consulta));
        }else {
            throw new IllegalArgumentException("Paciente ou médico inativo!");
        }
    }
    public Consulta validandoHorario(Consulta consulta) {
        if (consulta.getDataHora().getHour() >= horaInicio.getHour() &&
                consulta.getHoraFinal().getHour() <= horaTermino.getHour()) {
            return validandoMinutosAntes(consulta);
        }else {
            throw new IllegalArgumentException("Fora do horário de funcionamento da clínica!");
        }
    }

    public Consulta validandoMinutosAntes(Consulta consulta) {
        if (consulta.getDataHora().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
            if (consulta.getDataHora().toLocalTime().minusMinutes(30).isAfter(LocalDateTime.now().toLocalTime())) {
                return consulta;
            } else {
                throw new IllegalArgumentException("Consulta precisa ser marcada 30 minutos antes!");
            }
        }
        return consulta;
    }
    public Consulta conferirAgendamentoPaciente(Consulta consulta) {
        var consultas = consultaRepository.findAll();
        Optional<Consulta> consultaExistePaciente = consultas.stream()
                .filter(c -> c.getMedico().getId().equals(consulta.getMedico().getId()))
                .filter(c -> c.getDataHora().getDayOfMonth() == (consulta.getDataHora().getDayOfMonth()))
                .findFirst();

        if (consultaExistePaciente.isPresent()) {
            throw new IllegalArgumentException("Paciente já possui consulta marcada nesse dia");
        }
        return consulta;
    }




    /*Criar um método que tenha a consulta que quer ser marcada e saber se esse paciente já possui consulta
    no mesmo dia, é preciso saber se é possível buscar no banco sobre essa informação com repository*/
}
