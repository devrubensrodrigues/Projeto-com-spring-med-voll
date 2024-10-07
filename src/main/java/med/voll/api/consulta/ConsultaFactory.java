package med.voll.api.consulta;

import med.voll.api.medico.Medico;
import med.voll.api.paciente.Paciente;

public class ConsultaFactory {

    public static Consulta validarParametrosConsulta(Paciente paciente, Medico medico, String dataHora) {
        if (medico.getAtivo() && paciente.isAtivo()) {
            return new Consulta(paciente, medico, dataHora);
        }else {
            throw new IllegalArgumentException("Paciente ou médico inativo");
        }
    }

}
