package med.voll.api.servico;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Servico {
    @Autowired
    private MedicoRepository repository;

    public void addDadosMedico(DadosCadastroMedico dados) {
        Medico medico = new Medico(dados);
        verificarEndereco(medico.getEndereco());
        repository.save(medico);
    }

    public Page<DadosListagemMedico> buscaMedicos(Pageable pageable) {
        return repository.findAll(pageable).map(DadosListagemMedico::new);
    }

    private Endereco verificarEndereco(Endereco endereco) {
        Optional<Endereco> enderecoExistente = repository.buscarEndereco(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getNumero());

        return enderecoExistente.orElse(endereco);
    }
}
