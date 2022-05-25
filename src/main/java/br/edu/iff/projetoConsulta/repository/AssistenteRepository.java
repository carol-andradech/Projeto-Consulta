package br.edu.iff.projetoConsulta.repository;

import br.edu.iff.projetoConsulta.model.Assistente;
import br.edu.iff.projetoConsulta.model.Pessoa;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface AssistenteRepository extends JpaRepository<Assistente, Long> {
    @Query("SELECT p from Pessoa p where  p.cpf = :cpf OR p.email = :email")
    public List<Pessoa> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);
}
