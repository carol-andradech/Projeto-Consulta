package br.edu.iff.projetoConsulta.service;

import br.edu.iff.projetoConsulta.exception.NotFoundException;
import br.edu.iff.projetoConsulta.model.Assistente;
import br.edu.iff.projetoConsulta.model.Pessoa;
import br.edu.iff.projetoConsulta.repository.AssistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class AssistenteService {

    @Autowired
    private AssistenteRepository repo;

    public List<Assistente> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }

    public List<Assistente> findAll(){
        return repo.findAll();
    }

    public Assistente findById(Long id){
        Optional<Assistente> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Assistente não encontrado.");
        }
        return result.get();
    }

    public Assistente save(Assistente a){

        verificaCpfEmailCadastrado(a.getCpf(), a.getEmail());
        try {
            return repo.save(a);
        }catch (Exception ex){
            throw new RuntimeException("Falha ao salvar Assistente. ");
        }
    }

    public Assistente update(Assistente a){
        //Verifica se assistente já existe
        Assistente obj = findById(a.getId());
        //Verifica alteração da senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try{
            a.setCpf(obj.getCpf());
            a.setEmail(obj.getEmail());
            a.setSenha(obj.getSenha());
            return repo.save(a);
        }catch (Exception ex){
            Throwable t = ex;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar Assistente. ");
        }
    }

    public void delete(Long id){
        Assistente obj = findById(id);
        try {
            repo.delete(obj);
        }catch (Exception ex){
            throw new RuntimeException("Falha ao excluir Assistente. ");
        }

    }

    //Verifica se o email ou cpf já estão cadastrados
    private void verificaCpfEmailCadastrado(String cpf, String email){
        List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
        if(!result.isEmpty()){
            throw new RuntimeException("CPF ou Email já cadastrados.");
        }
    }

    private void alterarSenha(Assistente obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()){
            if(!senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("Senha atual está incorreta. ");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("Senhas não conferem. ");
            }
            obj.setSenha(novaSenha);
        }
    }

}
