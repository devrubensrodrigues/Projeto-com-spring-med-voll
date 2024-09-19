package med.voll.api.servico;

import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;
import med.voll.api.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {
    @Autowired
    private Repository repository;

    public void addDadosMedico(DadosCadastroMedico dados) {
        repository.save(converteMedicoDadosMedico(dados));
    }

    private Medico converteMedicoDadosMedico(DadosCadastroMedico dados) {
        Medico medico = new Medico(dados.nome(), dados.email(), dados.crm(), dados.especialidade(),
                new Endereco(dados.endereco().logradouro(), dados.endereco().bairro(), dados.endereco().cep(), dados.endereco().cidade(), dados.endereco().uf(), dados.endereco().numero(), dados.endereco().complemento()));
        return medico;
    }
}
