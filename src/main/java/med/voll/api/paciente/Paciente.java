package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.consulta.Consulta;
import med.voll.api.endereco.Endereco;

import java.util.List;

@Entity
@Table(name = "tb_pacientes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"telefone", "cpf"})
})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private boolean ativo;
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados);
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizaPaciente dados) {
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

    @Override
    public String toString() {
        return "Paciente" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco;
    }
}
