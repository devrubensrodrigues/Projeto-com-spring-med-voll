package med.voll.api.servico;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Servico {
    @Autowired
    private MedicoRepository repository;

    public void addDadosMedico(DadosCadastroMedico dados) {
        repository.save(converteMedicoDadosMedico(dados));
    }

    private Medico converteMedicoDadosMedico(DadosCadastroMedico dados) {
        Medico medico = new Medico(dados.nome(), dados.email(), dados.telefone(), dados.crm(), dados.especialidade(),
                verificarEndereco(new Endereco(dados.endereco().logradouro(), dados.endereco().bairro(), dados.endereco().cep(), dados.endereco().cidade(), dados.endereco().uf(), dados.endereco().numero(), dados.endereco().complemento())));
        return medico;
    }

    private Endereco verificarEndereco(Endereco endereco) {
        Optional<Endereco> enderecoExistente = repository.buscarEndereco(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getNumero());

        return enderecoExistente.orElse(endereco);
    }
}
