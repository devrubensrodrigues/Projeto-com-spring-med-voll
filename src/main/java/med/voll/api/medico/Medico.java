package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.consulta.Consulta;
import med.voll.api.endereco.Endereco;

;import java.util.List;

@Entity
@Table(name = "tb_medicos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"telefone", "crm"})
})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private Boolean ativo;
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados);
    }

    public void atualizarInformacoes(DadosAtualizaMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluirMedico() {
        this.ativo = false;
    }

    @Override
    public String toString() {
        return "nome: '" + nome + '\'' +
                ", email: '" + email + '\'' +
                ", crm: '" + crm + '\'' +
                ", especialidade: " + especialidade;
    }
}
