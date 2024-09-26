package med.voll.api.servico;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosAtualizaMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Servico {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRrepository;

    public void addDadosMedico(DadosCadastroMedico dados) {
        Medico medico = new Medico(dados);
        verificarEndereco(medico.getEndereco());
        medicoRepository.save(medico);
    }

    public Page<DadosListagemMedico> buscaMedicos(Pageable pageable) {
        return medicoRepository.findByAtivoTrue(pageable)
                .map(DadosListagemMedico::new);
    }

    public void atualizaDadosMedico(DadosAtualizaMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    public void excluirMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluirMedico();
    }

    public void registrarPaciente(DadosCadastroPaciente dados) {
        Paciente paciente = new Paciente(dados);
        verificarEndereco(paciente.getEndereco());
        pacienteRrepository.save(paciente);
    }

    private Endereco verificarEndereco(Endereco endereco) {
        Optional<Endereco> enderecoExistente = medicoRepository.buscarEndereco(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getNumero());

        return enderecoExistente.orElse(endereco);
    }
}
