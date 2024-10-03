package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.consulta.DadosCadastroConsulta;
import med.voll.api.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private Servico servico;

    @PostMapping
    @Transactional
    public void agendarConsulta(@RequestBody @Valid DadosCadastroConsulta dados) {
        servico.agendarConsulta(dados);
    }
}
