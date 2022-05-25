package br.edu.iff.projetoConsulta.repository;

import br.edu.iff.projetoConsulta.model.Consulta;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    //Quero buscar todas as consultas, por psicolog, por paciente e assistente
    @Query("SELECT c FROM Consulta c WHERE c.id = :id")
    public List<Consulta> findAllById(@Param("id") Long id);

    public List<Consulta> findByPacienteId(Long pacienteId, Pageable page);

    public List<Consulta> findByPsicologoId(Long psicologoId, Pageable page);

    public List<Consulta> findByAssistenteId(Long assistenteId, Pageable page);

    public List<Consulta> findByAssistenteIdAndPacienteIdAndPsicologoId(Long assistenteId, Long pacienteId, Long PsicologoId, Pageable page);

}
