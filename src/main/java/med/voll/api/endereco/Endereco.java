package med.voll.api.endereco;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.Medico;

import java.util.List;

@Entity
@Table(name = "tb_enderecos")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    @Column(unique = true)
    private String numero;
    private String complemento;
    @OneToMany(mappedBy = "endereco")
    private List<Medico> medico;

    public Endereco(String logradouro, String bairro, String cep, String cidade, String uf, String numero, String complemento) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.numero = numero;
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
