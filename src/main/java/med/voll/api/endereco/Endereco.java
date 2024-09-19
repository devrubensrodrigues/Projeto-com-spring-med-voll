package med.voll.api.endereco;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.DadosCadastroMedico;
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

    public Endereco(DadosCadastroMedico dados) {
        this.logradouro = dados.endereco().logradouro();
        this.bairro = dados.endereco().bairro();
        this.cep = dados.endereco().cep();
        this.cidade = dados.endereco().cidade();
        this.uf = dados.endereco().uf();
        this.numero = dados.endereco().numero();
        this.complemento = dados.endereco().complemento();
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
