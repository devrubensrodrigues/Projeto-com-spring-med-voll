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

    public Medico(String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade=" + especialidade +
                ", endereco=" + endereco +
                '}';
    }
}
