package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

;

@Entity
@Table(name = "tb_medicos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"telefone", "crm"})
})
@Getter
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
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados);
    }

    @Override
    public String toString() {
        return "nome: '" + nome + '\'' +
                ", email: '" + email + '\'' +
                ", crm: '" + crm + '\'' +
                ", especialidade: " + especialidade;
    }
}
