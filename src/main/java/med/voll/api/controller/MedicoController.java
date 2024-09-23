package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private Servico servico;
    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody @Valid DadosCadastroMedico dados) {
        servico.addDadosMedico(dados);
    }

    @GetMapping
    public Page<DadosListagemMedico> buscaMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return servico.buscaMedicos(pageable);
    }
}
