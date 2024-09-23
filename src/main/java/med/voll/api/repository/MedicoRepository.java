package med.voll.api.repository;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("SELECT e FROM Medico m JOIN m.endereco e WHERE e.logradouro = :logradouro AND e.bairro = :bairro AND e.cep = :cep AND e.cidade = :cidade AND e.uf = :uf AND e.numero != :numero")
    Optional<Endereco> buscarEndereco(String logradouro, String bairro, String cep, String cidade, String uf, String numero);

    //@Query("SELECT m FROM Medico m ORDER BY m.nome LIMIT 10")
    //List<Medico> buscarMedicosEmOrdem();
}
