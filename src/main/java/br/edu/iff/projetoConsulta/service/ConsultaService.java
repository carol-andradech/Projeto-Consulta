package br.edu.iff.projetoConsulta.service;

import br.edu.iff.projetoConsulta.exception.NotFoundException;
import br.edu.iff.projetoConsulta.model.Consulta;
import br.edu.iff.projetoConsulta.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.validation.ConstraintViolationException;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repo;

    public List<Consulta> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }

    public List<Consulta> findAll(){
        return repo.findAll();
    }

    public List<Consulta> findAll(int page, int size, Long assistenteId, Long pacienteId, Long psicologoId){
        Pageable p = PageRequest.of(page, size);
        if(assistenteId!=0 && pacienteId!=0 && psicologoId != 0){
            return repo.findByAssistenteIdAndPacienteIdAndPsicologoId(assistenteId, pacienteId, psicologoId, p);
        } else if(assistenteId!=0){
            return repo.findByAssistenteId(assistenteId, p);
        } else if(pacienteId!=0){
            return repo.findByPacienteId(pacienteId, p);
        } else if(psicologoId!=0){
            return repo.findByPsicologoId(psicologoId, p);
        }

        return repo.findAll(p).toList();
    }

    public Consulta findById(Long id){
        Optional<Consulta> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Consulta não encontrada. ");
        }
        return result.get();
    }

    public Consulta save(Consulta c){
        try {
            return repo.save(c);
        }catch (Exception ex){
            throw new RuntimeException("Falha ao salvar Consulta.");
        }
    }

    public Consulta update(Consulta c){
        //Verifica se já existe
        Consulta obj = findById(c.getId());
        try {
            c.setId(obj.getId());
            return repo.save(c);
        } catch (Exception ex) {
            Throwable t = ex;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar a Consulta.");
        }
    }

    public void delete(Long id){
        Consulta obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception c){
            throw new RuntimeException("Falha ao deletar a Consulta.");
        }
    }
}
