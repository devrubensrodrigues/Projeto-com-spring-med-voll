package med.voll.api.endereco;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.DadosCadastroPaciente;

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

    public Endereco(DadosCadastroMedico dados) {
        this.logradouro = dados.endereco().logradouro();
        this.bairro = dados.endereco().bairro();
        this.cep = dados.endereco().cep();
        this.cidade = dados.endereco().cidade();
        this.uf = dados.endereco().uf();
        this.numero = dados.endereco().numero();
        this.complemento = dados.endereco().complemento();
    }

    public Endereco(DadosCadastroPaciente dados) {
        this.logradouro = dados.endereco().logradouro();
        this.bairro = dados.endereco().bairro();
        this.cep = dados.endereco().cep();
        this.cidade = dados.endereco().cidade();
        this.uf = dados.endereco().uf();
        this.numero = dados.endereco().numero();
        this.complemento = dados.endereco().complemento();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
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
