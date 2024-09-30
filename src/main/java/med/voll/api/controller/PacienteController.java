package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosAtualizaPaciente;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private Servico servico;

    @PostMapping
    @Transactional
    public void registrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        servico.registrarPaciente(dados);
    }

    @GetMapping
    public Page<DadosListagemPaciente> buscarPacientes(@PageableDefault(size = 10, sort = {"nome"})Pageable pageable) {
        return servico.buscaPacientes(pageable);
    }

    @PutMapping
    @Transactional
    public void atualizaPaciente(@RequestBody @Valid DadosAtualizaPaciente dados) {
        servico.atualizaDadosPaciente(dados);
    }
}
