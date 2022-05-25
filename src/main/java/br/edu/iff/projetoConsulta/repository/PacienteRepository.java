package br.edu.iff.projetoConsulta.repository;

import br.edu.iff.projetoConsulta.model.Paciente;
import br.edu.iff.projetoConsulta.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Pessoa p WHERE p.cpf = :cpf OR p.email = :email")
    public List<Pessoa> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);

    public Paciente findByEmail(String email);
}
