package br.edu.iff.projetoConsulta.service;

import br.edu.iff.projetoConsulta.exception.NotFoundException;
import br.edu.iff.projetoConsulta.model.Paciente;
import br.edu.iff.projetoConsulta.model.Pessoa;
import br.edu.iff.projetoConsulta.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repo;

    public List<Paciente> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }

    public List<Paciente> findAll(){
        return repo.findAll();
    }

    public Paciente findById(Long id){
        Optional<Paciente> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Paciente não encontrado. ");
        }
        return result.get();
    }

    public Paciente save(Paciente p){
        verificaCpfEmailCadastrado(p.getCpf(), p.getEmail());

        try{
            return repo.save(p);
        }catch (Exception ex){
            throw new RuntimeException("Falha ao salvar Paciente. ");
        }
    }

    public Paciente update(Paciente p){
        //Verifica se assistente já existe
        Paciente obj = findById(p.getId());
        try{
            p.setCpf(obj.getCpf());
            p.setEmail(obj.getEmail());
            return repo.save(p);
        }catch (Exception ex){
            throw new RuntimeException("Falha ao atualizar Assistente. ");
        }
    }

    public void delete(Long id){
        Paciente obj = findById(id);
        //Verifica se há consultas marcadas
        verificaExclusaoPacienteComConsulta(obj);

        try {
            repo.delete(obj);
        } catch (Exception p) {
            throw new RuntimeException("Falha ao excluir Paciente. ");
        }


    }

    //Verifica se o email ou cpf já estão cadastrados
    private void verificaCpfEmailCadastrado(String cpf, String email){
        List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
        if(!result.isEmpty()){
            throw new RuntimeException("CPF ou Email já cadastrados.");
        }
    }

    private void verificaExclusaoPacienteComConsulta(Paciente p){
        if(!p.getConsultas().isEmpty()){
            throw new RuntimeException("Paciente possui Consulta marcada. Não pode ser excluido.");
        }
    }


}
